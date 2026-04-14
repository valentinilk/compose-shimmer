package com.valentinilk.shimmer

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shader
import androidx.compose.ui.graphics.asComposeShader
import androidx.compose.ui.graphics.toArgb
import org.jetbrains.skia.Shader as SkShader

internal actual fun createLinearGradientShader(
    from: Offset,
    to: Offset,
    colors: List<Color>,
    colorStops: List<Float>?,
): Shader {
    val skiaColors = IntArray(colors.size) { index ->
        colors[index].toArgb()
    }
    return SkShader.makeLinearGradient(
        x0 = from.x,
        y0 = from.y,
        x1 = to.x,
        y1 = to.y,
        colors = skiaColors,
        positions = colorStops?.toFloatArray(),
    ).asComposeShader()
}
