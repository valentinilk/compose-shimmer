import com.vanniktech.maven.publish.SonatypeHost
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSetTree

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.dokka)
    alias(libs.plugins.publish)
}

android {
    compileSdk = libs.versions.targetSdk.get().toInt()
    namespace = "com.valentinilk.shimmer"

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

kotlin {
    applyHierarchyTemplate {
        withSourceSetTree(KotlinSourceSetTree.main, KotlinSourceSetTree.test)
        common {
            group("skiko") {
                withIos()
                withJs()
                withJvm()
                withWasm()
            }
            withAndroidTarget()
        }
    }

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

    wasmJs {
        browser()
    }

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        commonMain.dependencies {
            implementation(compose.foundation)
        }
        commonTest.dependencies {
            implementation(kotlin("test"))
            implementation(libs.kotlinx.coroutines.test)
        }
    }
}

mavenPublishing {
    coordinates(
        "com.valentinilk.shimmer",
        "compose-shimmer",
        "1.3.1",
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
