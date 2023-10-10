package com.valentinilk.shimmer

import androidx.compose.runtime.Composable

data class ScreenInfo(
    val width: Int,
    val height: Int,
)

@Composable
expect fun rememberScreenInfo(): ScreenInfo