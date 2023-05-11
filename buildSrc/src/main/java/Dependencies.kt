object Config {
    const val minSdk = 21
    const val targetSdk = 33
}

object Lib {

    const val androidGradlePlugin = "com.android.tools.build:gradle:7.4.0"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.21"

    object Compose {
        private const val composeBomVersion = "2023.05.01"
        const val composeBom = "androidx.compose:compose-bom:$composeBomVersion"

        const val compiler = "1.4.7"

        const val runtime = "androidx.compose.runtime:runtime"

        const val ui = "androidx.compose.ui:ui"
        const val tooling = "androidx.compose.ui:ui-tooling"

        const val foundation = "androidx.compose.foundation:foundation"

        const val material = "androidx.compose.material:material"
        const val icons = "androidx.compose.material:material-icons-extended"

        const val navigation = "androidx.navigation:navigation-compose:2.5.3"
    }

    object Google {
        const val material = "com.google.android.material:material:1.7.0"
    }
}
