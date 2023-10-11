package com.valentinilk.shimmer

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.geometry.Rect
import kotlinx.cinterop.ExperimentalForeignApi
import platform.CoreGraphics.CGRectGetHeight
import platform.CoreGraphics.CGRectGetWidth
import platform.UIKit.UIScreen

@OptIn(ExperimentalForeignApi::class)
@Composable
internal actual fun rememberWindowBounds(): Rect {
    return remember {
        val width = CGRectGetWidth(UIScreen.mainScreen.nativeBounds).toFloat()
        val height = CGRectGetHeight(UIScreen.mainScreen.nativeBounds).toFloat()
        Rect(0f, 0f, width, height)
    }
}