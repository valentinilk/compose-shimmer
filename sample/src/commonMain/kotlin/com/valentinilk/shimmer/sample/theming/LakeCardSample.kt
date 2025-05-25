package com.valentinilk.shimmer.sample.theming

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.valentinilk.shimmer.LocalShimmerTheme
import com.valentinilk.shimmer.defaultShimmerTheme
import com.valentinilk.shimmer.shimmer
import com.valentinilk.shimmer.shimmerSpec
import compose_shimmer.sample.generated.resources.Res
import compose_shimmer.sample.generated.resources.lake_after
import compose_shimmer.sample.generated.resources.lake_before
import org.jetbrains.compose.resources.painterResource

private val lakeCardTheme = defaultShimmerTheme.copy(
    animationSpec = infiniteRepeatable(
        animation = shimmerSpec(
            durationMillis = 6_500,
            delayMillis = 5_500,
            easing = LinearEasing,
        ),
    ),
    blendMode = BlendMode.DstIn,
    rotation = 0f,
    shaderColors = listOf(
        Color.Unspecified.copy(alpha = 0.0f),
        Color.Unspecified.copy(alpha = 1.0f),
        Color.Unspecified.copy(alpha = 1.0f),
        Color.Unspecified.copy(alpha = 0.0f),
    ),
    shaderColorStops = listOf(
        0.0f,
        0.1f,
        0.9f,
        1.0f,
    ),
    shimmerWidth = 1_000.dp,
)

@Composable
fun LakeCardSample() {
    CompositionLocalProvider(
        LocalShimmerTheme provides lakeCardTheme,
    ) {
        ThemingSampleCard {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = painterResource(Res.drawable.lake_before),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                )
                Image(
                    modifier = Modifier
                        .fillMaxSize()
                        .shimmer(),
                    painter = painterResource(Res.drawable.lake_after),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                )
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.BottomEnd,
                ) {
                    Text(
                        modifier = Modifier.padding(end = 4.dp),
                        text = "(C) climate.nasa.gov",
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = Color.White,
                            fontSize = 8.sp,
                        ),
                    )
                }
            }
        }
    }
}
