import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.composeMultiplatform)
}

android {
    compileSdk = Config.targetSdk
    namespace = "com.valentinilk.shimmer.shared"
}

kotlin {
    @Suppress("OPT_IN_USAGE")
    targetHierarchy.default()

    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = JavaVersion.VERSION_1_8.toString()
            }
        }
    }

    jvm("desktop")
    js(IR) {
        browser()
        binaries.executable()
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                arrayOf(
                    projects.shimmer,
                    compose.runtime,
                    compose.foundation,
                    compose.ui,
                    compose.material3,
                    libs.atomicfu,
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

compose.experimental {
    web.application {}
}
