package com.valentinilk.shimmer

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Rect
import kotlinx.browser.window
import org.w3c.dom.events.Event

@Composable
internal actual fun rememberWindowBounds(): Rect {
    var rect by remember {
        mutableStateOf(
            Rect(0f, 0f, window.innerWidth.toFloat(), window.innerHeight.toFloat()),
        )
    }

    DisposableEffect(Unit) {
        val onResize: (Event) -> Unit = {
            rect =
                Rect(0f, 0f, window.innerWidth.toFloat(), window.innerHeight.toFloat())
        }

        window.addEventListener(type = "resize", onResize)
        onDispose {
            window.removeEventListener(type = "resize", onResize)
        }
    }

    return rect
}
