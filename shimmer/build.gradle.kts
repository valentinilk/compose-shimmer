plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    compileSdk = Config.targetSdk

    defaultConfig {
        minSdk = Config.minSdk
        targetSdk = Config.targetSdk
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
        Lib.Compose.runtime,
        Lib.Compose.foundation,
        Lib.Compose.ui,
        Lib.Compose.tooling
    ).forEach { dependency ->
        implementation(dependency)
    }
}
