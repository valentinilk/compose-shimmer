package com.valentinilk.shimmer.sample.theming

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Downloading
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.valentinilk.shimmer.LocalShimmerTheme
import com.valentinilk.shimmer.defaultShimmerTheme
import com.valentinilk.shimmer.shimmer

private val loadingCardTheme = defaultShimmerTheme.copy(
    animationSpec = infiniteRepeatable(
        animation = tween(
            durationMillis = 600,
            delayMillis = 2_400,
            easing = LinearEasing,
        ),
        repeatMode = RepeatMode.Restart,
    ),
    rotation = 5f,
    shaderColors = listOf(
        Color.Unspecified.copy(alpha = 1.0f),
        Color.Unspecified.copy(alpha = 0.2f),
        Color.Unspecified.copy(alpha = 1.0f),
    ),
    shaderColorStops = null,
    shimmerWidth = 200.dp,
)

@Composable
fun LoadingCardSample() {
    CompositionLocalProvider(
        LocalShimmerTheme provides loadingCardTheme
    ) {
        ThemingSampleCard {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(245, 245, 245)),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier.shimmer(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Image(
                        modifier = Modifier.size(64.dp),
                        imageVector = Icons.Outlined.Downloading,
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )
                    Text("LOADING", style = MaterialTheme.typography.h4)
                }
            }
        }
    }
}
