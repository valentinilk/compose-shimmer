package com.valentinilk.shimmer

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationConstants.DefaultDurationMillis
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.KeyframesSpec
import androidx.compose.animation.core.LinearEasing
import androidx.compose.runtime.Stable

/**
 * Creates a [KeyframesSpec] which animates from 0f to 1f during the time span defined in [durationMillis].
 * The additional [delayMillis] define how long the animation will stay at its final value of 1f before the animation
 * ends.
 * The spec will ignore any initial or target value defined in an [Animatable].
 *
 * @param durationMillis duration of the animation spec. Excluding the [delayMillis].
 * @param delayMillis additional amount of time the animation will stay at 1f before ending.
 * @param easing the easing curve that will be used to interpolate between 0f and 1f.
 */
@Stable
fun shimmerSpec(
    durationMillis: Int = DefaultDurationMillis,
    delayMillis: Int = 0,
    easing: Easing = LinearEasing,
) = KeyframesSpec(
    KeyframesSpec.KeyframesSpecConfig<Float>().apply {
        0f at 0 using easing
        1f at durationMillis
        if (delayMillis > 0) {
            1f at durationMillis + delayMillis
        }
        this.durationMillis = durationMillis + delayMillis
    },
)
