package com.valentinilk.shimmer

import androidx.compose.ui.Modifier

fun Modifier.shimmer(
    enabled: Boolean,
    customShimmer: Shimmer? = null
) : Modifier = if (enabled) shimmer(customShimmer) else this