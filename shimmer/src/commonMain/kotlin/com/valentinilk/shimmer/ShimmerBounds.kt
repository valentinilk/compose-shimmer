package com.valentinilk.shimmer

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.geometry.Rect

@Composable
internal fun rememberShimmerBounds(
    shimmerBounds: ShimmerBounds,
): Rect? {
    val screenInfo = shimmerBounds.rememberScreenInfoOrNull()
    return remember(shimmerBounds) {
        when (shimmerBounds) {
            ShimmerBounds.Window -> {
                check(screenInfo != null) {
                    "ShimmerBounds.Window requires a valid screenInfo"
                }

                Rect(
                    0f,
                    0f,
                    screenInfo.width.toFloat(),
                    screenInfo.height.toFloat()
                )
            }
            ShimmerBounds.Custom -> Rect.Zero
            ShimmerBounds.View -> null
        }
    }
}

sealed interface ShimmerBounds {
    data object Custom : ShimmerBounds
    data object View : ShimmerBounds
    data object Window : ShimmerBounds
}
