package com.valentinilk.shimmer

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import kotlinx.browser.window
import org.w3c.dom.events.Event

@Composable
internal actual fun rememberScreenInfo(): ScreenInfo {
    var screenInfo by remember {
        mutableStateOf(ScreenInfo(window.innerWidth, window.innerHeight))
    }

    DisposableEffect(Unit) {
        val onResize: (Event) -> Unit = {
            screenInfo = ScreenInfo(
                width = window.innerWidth,
                height = window.innerHeight,
            )
        }

        window.addEventListener(type = "resize", onResize)
        onDispose {
            window.removeEventListener(type = "resize", onResize)
        }
    }

    return screenInfo
}