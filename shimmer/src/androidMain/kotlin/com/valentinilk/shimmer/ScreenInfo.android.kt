package com.valentinilk.shimmer

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext

@Composable
internal actual fun rememberScreenInfo(): ScreenInfo {
    val resources = LocalContext.current.resources

    return remember(resources) {
        val metrics = resources.displayMetrics

        ScreenInfo(
            width = metrics.widthPixels,
            height = metrics.heightPixels,
        )
    }
}
