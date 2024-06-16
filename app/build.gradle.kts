plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.composeCompiler)
}

android {
    compileSdk = Config.targetSdk
    namespace = "com.valentinilk.shimmer.sample"

    defaultConfig {
        applicationId = "com.valentinilk.shimmer.sample"

        minSdk = Config.minSdk
        targetSdk = Config.targetSdk

        versionCode = 1
        versionName = "1.0"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        compose = true
    }
}

dependencies {
    arrayOf(
        projects.shimmer,
        libs.compose.bom,
        libs.compose.material,
        libs.compose.material.icons,
        libs.compose.navigation,
        libs.compose.runtime,
        libs.compose.foundation,
        libs.compose.ui,
        libs.compose.tooling,
        libs.material,
    ).forEach { dependency ->
        implementation(dependency)
    }
}
