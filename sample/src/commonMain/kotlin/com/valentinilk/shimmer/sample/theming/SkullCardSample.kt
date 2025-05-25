package com.valentinilk.shimmer.sample.theming

import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.valentinilk.shimmer.LocalShimmerTheme
import com.valentinilk.shimmer.defaultShimmerTheme
import com.valentinilk.shimmer.shimmer
import com.valentinilk.shimmer.shimmerSpec
import compose_shimmer.sample.generated.resources.Res
import compose_shimmer.sample.generated.resources.skull
import org.jetbrains.compose.resources.painterResource

private val skullCardTheme = defaultShimmerTheme.copy(
    animationSpec = infiniteRepeatable(
        animation = shimmerSpec(
            durationMillis = 2_500,
            delayMillis = 3_000,
            easing = CubicBezierEasing(0f, 0.8f, 1.0f, 0.2f),
        ),
    ),
    blendMode = BlendMode.DstIn,
    rotation = 0f,
    shaderColors = listOf(
        Color.Unspecified.copy(alpha = 0.0f),
        Color.Unspecified.copy(alpha = 1.0f),
        Color.Unspecified.copy(alpha = 0.0f),
    ),
    shaderColorStops = null,
    shimmerWidth = 1_000.dp,
)

@Composable
fun SkullCardSample() {
    CompositionLocalProvider(
        LocalShimmerTheme provides skullCardTheme,
    ) {
        ThemingSampleCard {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black),
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxSize()
                        .shimmer(),
                    painter = painterResource(Res.drawable.skull),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                )
            }
        }
    }
}
