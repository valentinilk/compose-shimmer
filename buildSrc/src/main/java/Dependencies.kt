object Config {
    const val minSdk = 21
    const val targetSdk = 31
}

object Lib {

    const val androidGradlePlugin = "com.android.tools.build:gradle:7.2.0"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.10"

    object Compose {
        const val version = "1.1.1"

        const val runtime = "androidx.compose.runtime:runtime:$version"
        const val ui = "androidx.compose.ui:ui:$version"
        const val tooling = "androidx.compose.ui:ui-tooling:$version"
        const val foundation = "androidx.compose.foundation:foundation:$version"
        const val material = "androidx.compose.material:material:$version"
        const val icons = "androidx.compose.material:material-icons-extended:$version"

        const val navigation = "androidx.navigation:navigation-compose:2.4.2"
    }

    object Google {
        const val material = "com.google.android.material:material:1.6.0"
    }
}
