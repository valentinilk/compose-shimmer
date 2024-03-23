package com.valentinilk.shimmer

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.toSize

@OptIn(ExperimentalComposeUiApi::class)
@Composable
internal actual fun rememberWindowBounds(): Rect {
    val windowInfo = LocalWindowInfo.current.containerSize

    return remember(windowInfo) {
        Rect(Offset.Zero, windowInfo.toSize())
    }
}
