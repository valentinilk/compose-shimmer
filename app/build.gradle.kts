plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    compileSdk = Config.targetSdk

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

    composeOptions {
        kotlinCompilerExtensionVersion = Lib.Compose.version
    }
}

dependencies {
    arrayOf(
        project(":shimmer"),
        Lib.Compose.material,
        Lib.Compose.icons,
        Lib.Compose.navigation,
        Lib.Compose.runtime,
        Lib.Compose.foundation,
        Lib.Compose.ui,
        Lib.Compose.tooling,
        Lib.Google.material,
    ).forEach { dependency ->
        implementation(dependency)
    }
}
