package com.valentinilk.shimmer

import androidx.compose.ui.Modifier

fun Modifier.shimmer(
    enabled: Boolean
) : Modifier = if (enabled) shimmer() else this