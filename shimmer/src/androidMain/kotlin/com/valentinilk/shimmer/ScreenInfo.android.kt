package com.valentinilk.shimmer

import android.content.res.Resources
import androidx.compose.runtime.Composable

@Composable
internal actual fun rememberScreenInfo(): ScreenInfo {
    val metrics = Resources.getSystem().displayMetrics

    return ScreenInfo(
        width = metrics.widthPixels,
        height = metrics.heightPixels,
    )
}
