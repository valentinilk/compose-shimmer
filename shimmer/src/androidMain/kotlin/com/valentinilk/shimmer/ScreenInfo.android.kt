package com.valentinilk.shimmer

import android.content.res.Resources
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.geometry.Rect

@Composable
internal actual fun rememberWindowBounds(): Rect = remember {
    val metrics = Resources.getSystem().displayMetrics

    Rect(0f, 0f, metrics.widthPixels.toFloat(), metrics.heightPixels.toFloat())
}
