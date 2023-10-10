package com.valentinilk.shimmer

import kotlinx.cinterop.ExperimentalForeignApi
import platform.CoreGraphics.CGRectGetHeight
import platform.CoreGraphics.CGRectGetWidth
import platform.UIKit.UIScreen

@OptIn(ExperimentalForeignApi::class)
actual object ScreenInfo {
    actual val width: Int = CGRectGetWidth(UIScreen.mainScreen.nativeBounds).toInt()
    actual val height: Int = CGRectGetHeight(UIScreen.mainScreen.nativeBounds).toInt()
}
