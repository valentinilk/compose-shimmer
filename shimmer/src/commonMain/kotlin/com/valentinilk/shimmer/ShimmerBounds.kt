package com.valentinilk.shimmer

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.platform.LocalContext

@Composable
internal fun rememberShimmerBounds(
    shimmerBounds: ShimmerBounds,
): Rect? {
    val displayMetrics = LocalContext.current.resources.displayMetrics
    return remember(shimmerBounds, displayMetrics) {
        when (shimmerBounds) {
            ShimmerBounds.Window -> Rect(
                0f,
                0f,
                displayMetrics.widthPixels.toFloat(),
                displayMetrics.heightPixels.toFloat()
            )
            ShimmerBounds.Custom -> Rect.Zero
            ShimmerBounds.View -> null
        }
    }
}

sealed class ShimmerBounds {
    object Custom : ShimmerBounds()
    object View : ShimmerBounds()
    object Window : ShimmerBounds()
}
