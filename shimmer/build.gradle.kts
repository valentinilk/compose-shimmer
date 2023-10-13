import com.vanniktech.maven.publish.SonatypeHost

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.dokka)
    alias(libs.plugins.publish)
}

android {
    compileSdk = Config.targetSdk
    namespace = "com.valentinilk.shimmer"

    defaultConfig {
        minSdk = Config.minSdk
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }
}

kotlin {
    @Suppress("OPT_IN_USAGE")
    targetHierarchy.default()

    androidTarget {
        publishLibraryVariants("release")
        compilations.all {
            kotlinOptions {
                jvmTarget = JavaVersion.VERSION_1_8.toString()
            }
        }
    }

    jvm()

    js(IR) {
        browser()
    }

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        val commonMain by getting {
            dependencies {
                arrayOf(
                    compose.runtime,
                    compose.foundation,
                    compose.ui,
                    libs.atomicfu,
                ).forEach { dependency ->
                    implementation(dependency)
                }
            }
        }
    }
}

mavenPublishing {
    coordinates(
        "com.valentinilk.shimmer",
        "compose-shimmer",
        "1.2.0",
    )
    pom {
        name.set("Compose Shimmer")
        description.set("Shimmer library for Jetpack Compose and Compose Multiplatform")
        url.set("https://github.com/valentinilk/compose-shimmer/")
        inceptionYear.set("2021")

        scm {
            connection.set("scm:git:git://github.com/valentinilk/compose-shimmer.git")
            developerConnection.set("scm:git:git://github.com/valentinilk/compose-shimmer.git")
            url.set("https://github.com/valentinilk/compose-shimmer/")
        }

        licenses {
            license {
                name.set("The Apache License, Version 2.0")
                url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                distribution.set("repo")
            }
        }

        developers {
            developer {
                id.set("valentinilk")
                name.set("Valentin Ilk")
                url.set("https://github.com/valentinilk/")
            }
        }
    }
    publishToMavenCentral(SonatypeHost.S01)
    signAllPublications()
}
