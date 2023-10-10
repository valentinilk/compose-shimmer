package com.valentinilk.shimmer

import android.content.res.Resources

actual object ScreenInfo {
    private val metrics = Resources.getSystem().displayMetrics
    actual val width: Int = metrics.widthPixels
    actual val height: Int = metrics.heightPixels
}
