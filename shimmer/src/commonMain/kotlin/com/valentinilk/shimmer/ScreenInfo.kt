package com.valentinilk.shimmer

import androidx.compose.runtime.Composable

data class ScreenInfo(
    val width: Int,
    val height: Int,
)

@Composable
internal expect fun rememberScreenInfo(): ScreenInfo

@Composable
internal fun ShimmerBounds.rememberScreenInfoOrNull(): ScreenInfo? {
    return when (this) {
        ShimmerBounds.Custom -> null
        ShimmerBounds.View -> null
        ShimmerBounds.Window -> rememberScreenInfo()
    }
}