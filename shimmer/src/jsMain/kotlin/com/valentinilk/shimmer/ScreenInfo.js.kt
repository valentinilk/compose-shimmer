package com.valentinilk.shimmer

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import kotlinx.browser.window

@Composable
internal actual fun rememberScreenInfo(): ScreenInfo {
    return remember(window.innerWidth, window.innerHeight) {
        ScreenInfo(
            width = window.innerWidth,
            height = window.innerHeight,
        )
    }
}