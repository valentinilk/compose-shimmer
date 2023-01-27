object Config {
    const val minSdk = 21
    const val targetSdk = 33
}

object Lib {

    const val androidGradlePlugin = "com.android.tools.build:gradle:7.4.0"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.0"

    object Compose {
        const val compiler = "1.4.0"
        const val runtime = "androidx.compose.runtime:runtime:1.3.3"

        const val composeUi = "1.3.3"
        const val ui = "androidx.compose.ui:ui:$composeUi"
        const val tooling = "androidx.compose.ui:ui-tooling:$composeUi"

        const val foundation = "androidx.compose.foundation:foundation:1.3.1"

        const val composeMaterial = "1.3.1"
        const val material = "androidx.compose.material:material:$composeMaterial"
        const val icons = "androidx.compose.material:material-icons-extended:$composeMaterial"

        const val navigation = "androidx.navigation:navigation-compose:2.5.3"
    }

    object Google {
        const val material = "com.google.android.material:material:1.7.0"
    }
}
