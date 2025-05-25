@file:OptIn(ExperimentalWasmDsl::class)

import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
}

android {
    compileSdk = libs.versions.targetSdk.get().toInt()
    namespace = "com.valentinilk.shimmer.sample"

    defaultConfig {
        applicationId = "com.valentinilk.shimmer.sample"

        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()

        versionCode = 1
        versionName = "1.0"
    }
}

kotlin {
    applyDefaultHierarchyTemplate()

    androidTarget {
        compilerOptions {
            jvmTarget = JvmTarget.JVM_1_8
        }
    }

    jvm("desktop")

    js(IR) {
        browser()
        binaries.executable()
    }

    wasmJs {
        browser()
        binaries.executable()
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64(),
    ).forEach {
        it.binaries.framework {
            baseName = "Sample"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                arrayOf(
                    projects.shimmer,
                    compose.components.resources,
                    compose.foundation,
                    compose.material3,
                    compose.materialIconsExtended,
                    compose.runtime,
                    compose.ui,
                    libs.compose.navigation,
                ).forEach { dependency ->
                    implementation(dependency)
                }
            }
        }

        val desktopMain by getting {
            dependencies {
                implementation(compose.desktop.common)
                implementation(compose.desktop.currentOs)
            }
        }
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "com.valentinilk.shimmer.desktop"
            packageVersion = "1.0.0"
        }
    }
}
