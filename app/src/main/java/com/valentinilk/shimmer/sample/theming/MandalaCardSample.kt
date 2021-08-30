package com.valentinilk.shimmer.sample.theming

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.valentinilk.shimmer.LocalShimmerTheme
import com.valentinilk.shimmer.defaultShimmerTheme
import com.valentinilk.shimmer.sample.R
import com.valentinilk.shimmer.shimmer

private val mandalaCardTheme = defaultShimmerTheme.copy(
    animationSpec = infiniteRepeatable(
        animation = tween(
            durationMillis = 20_000,
            delayMillis = 0,
            easing = LinearEasing,
        ),
    ),
    blendMode = BlendMode.SrcIn,
    rotation = 0f,
    shaderColors = listOf(
        Color(255, 215, 0),
        Color(218, 165, 32),
        Color(205, 133, 63),
        Color(210, 105, 30),
        Color(139, 69, 19),
        Color(255, 140, 0),
        Color(240, 230, 140),
        Color(255, 215, 0),
    ),
    shaderColorStops = null,
    shimmerWidth = 10_000.dp,
)

@Composable
fun MandalaCardSample() {
    CompositionLocalProvider(
        LocalShimmerTheme provides mandalaCardTheme
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
                    painter = painterResource(R.drawable.mandala),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}
