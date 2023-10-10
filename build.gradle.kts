import org.jetbrains.dokka.gradle.DokkaMultiModuleTask

plugins {
    alias(libs.plugins.androidApplication).apply(false)
    alias(libs.plugins.androidLibrary).apply(false)
    alias(libs.plugins.kotlinAndroid).apply(false)
    alias(libs.plugins.kotlinMultiplatform).apply(false)
    alias(libs.plugins.publish).apply(false)
    alias(libs.plugins.dokka)
}

tasks.withType<DokkaMultiModuleTask>().configureEach {
    outputDirectory.set(projectDir.resolve("docs/api"))
}
