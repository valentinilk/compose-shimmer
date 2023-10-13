@file:Suppress("INVISIBLE_MEMBER", "INVISIBLE_REFERENCE")

package com.valentinilk.shimmer

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.window.LocalWindow
import java.awt.Window
import java.awt.event.ComponentAdapter
import java.awt.event.ComponentEvent

@Composable
internal actual fun rememberWindowBounds(): Rect {
    val window: Window = LocalWindow.current ?: return Rect.Zero

    var rect by remember(window) {
        mutableStateOf(Rect(0f, 0f, window.width.toFloat(), window.height.toFloat()))
    }

    // Add a listener and listen for componentResized events
    DisposableEffect(window) {
        val listener = object : ComponentAdapter() {
            override fun componentResized(event: ComponentEvent) {
                rect = Rect(0f, 0f, window.width.toFloat(), window.height.toFloat())
            }
        }

        window.addComponentListener(listener)

        onDispose {
            window.removeComponentListener(listener)
        }
    }

    return rect
}
