package com.valentinilk.shimmer

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.LinearGradientShader

internal actual fun createLinearGradientShader(
    from: Offset,
    to: Offset,
    colors: List<Color>,
    colorStops: List<Float>?
) = LinearGradientShader(
    from = from,
    to = to,
    colors = colors,
    colorStops = colorStops,
)
