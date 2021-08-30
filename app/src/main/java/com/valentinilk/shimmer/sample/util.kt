package com.valentinilk.shimmer.sample

import androidx.compose.animation.core.DurationBasedAnimationSpec
import androidx.compose.animation.core.InfiniteRepeatableSpec
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween

fun InfiniteRepeatableSpec<Float>.copy(
    animationSpec: DurationBasedAnimationSpec<Float>? = null,
): InfiniteRepeatableSpec<Float> = infiniteRepeatable(
    animation = animationSpec ?: this.animation,
    repeatMode = RepeatMode.Restart,
)

fun TweenSpec<Float>.copy(
    durationMillis: Int? = null,
    delayMillis: Int? = null,
): TweenSpec<Float> = tween(
    durationMillis = durationMillis ?: this.durationMillis,
    delayMillis = delayMillis ?: this.delay,
    easing = LinearEasing,
)
