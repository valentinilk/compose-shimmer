package com.valentinilk.shimmer

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import kotlinx.cinterop.ExperimentalForeignApi
import platform.CoreGraphics.CGRectGetHeight
import platform.CoreGraphics.CGRectGetWidth
import platform.UIKit.UIScreen

@OptIn(ExperimentalForeignApi::class)
@Composable
internal actual fun rememberScreenInfo(): ScreenInfo {
    return remember {
        val width: Int = CGRectGetWidth(UIScreen.mainScreen.nativeBounds).toInt()
        val height: Int = CGRectGetHeight(UIScreen.mainScreen.nativeBounds).toInt()
        ScreenInfo(width, height)
    }
}