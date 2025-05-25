package com.valentinilk.shimmer.sample.theming

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.valentinilk.shimmer.LocalShimmerTheme
import com.valentinilk.shimmer.defaultShimmerTheme
import com.valentinilk.shimmer.shimmer
import com.valentinilk.shimmer.shimmerSpec
import compose_shimmer.sample.generated.resources.Res
import compose_shimmer.sample.generated.resources.mail_unread
import org.jetbrains.compose.resources.painterResource

private val notificationCardTheme = defaultShimmerTheme.copy(
    animationSpec = infiniteRepeatable(
        animation = shimmerSpec(
            durationMillis = 1_000,
            delayMillis = 3_000,
            easing = LinearEasing,
        ),
    ),
    blendMode = BlendMode.Overlay,
    rotation = 330f,
    shaderColors = listOf(
        Color.Unspecified.copy(alpha = 0.01f),
        Color(255, 68, 68).copy(alpha = 0.6f),
        Color.Unspecified.copy(alpha = 0.01f),
    ),
    shaderColorStops = null,
    shimmerWidth = 800.dp,
)

@Composable
fun NotificationCardSample() {
    CompositionLocalProvider(
        LocalShimmerTheme provides notificationCardTheme,
    ) {
        ThemingSampleCard {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.DarkGray),
                contentAlignment = Alignment.Center,
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .shimmer(),
                )
                Icon(
                    modifier = Modifier.size(128.dp),
                    painter = painterResource(Res.drawable.mail_unread),
                    tint = Color.Unspecified,
                    contentDescription = null,
                )
            }
        }
    }
}
