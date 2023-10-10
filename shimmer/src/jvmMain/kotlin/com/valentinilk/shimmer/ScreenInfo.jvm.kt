@file:Suppress("INVISIBLE_MEMBER", "INVISIBLE_REFERENCE")

package com.valentinilk.shimmer

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.window.LocalWindow
import java.awt.Window
import java.awt.event.ComponentAdapter
import java.awt.event.ComponentEvent

@Composable
internal actual fun rememberScreenInfo(): ScreenInfo {
    val window: Window? = LocalWindow.current

    var size by remember(window) {
        mutableStateOf(ScreenInfo(window?.width ?: 0, window?.height ?: 0))
    }

    // Add a listener and listen for componentResized events
    DisposableEffect(window) {
        val listener = object : ComponentAdapter() {
            override fun componentResized(event: ComponentEvent) {
                size = ScreenInfo(window!!.width, window.height)
            }
        }

        window?.addComponentListener(listener)

        onDispose {
            window?.removeComponentListener(listener)
        }
    }

    return size
}