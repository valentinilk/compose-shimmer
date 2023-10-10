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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }

    publishing {
        singleVariant("release") {
            withSourcesJar()
            withJavadocJar()
        }
    }
}

kotlin {
    androidTarget()

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
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
    }
}

mavenPublishing {
    coordinates(
        "com.valentinilk.shimmer",
        "compose-shimmer",
        "1.1.0",
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
