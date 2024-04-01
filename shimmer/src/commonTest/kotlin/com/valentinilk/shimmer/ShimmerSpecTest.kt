package com.valentinilk.shimmer

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.KeyframesSpec
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.tween
import androidx.compose.runtime.MonotonicFrameClock
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.withContext
import kotlin.test.Test
import kotlin.test.assertEquals

class ShimmerSpecTest {

    @Test
    fun compareFastOutSlowInEasing() = runTest {
        val tweenSpec = tween<Float>(10_000, easing = FastOutSlowInEasing)
        val shimmerSpec = shimmerSpec(10_000, easing = FastOutSlowInEasing)

        compareSpecs(
            tweenSpec = tweenSpec,
            shimmerSpec = shimmerSpec,
        )
    }

    @Test
    fun compareLinearOutSlowInEasing() = runTest {
        val tweenSpec = tween<Float>(10_000, easing = LinearOutSlowInEasing)
        val shimmerSpec = shimmerSpec(10_000, easing = LinearOutSlowInEasing)

        compareSpecs(
            tweenSpec = tweenSpec,
            shimmerSpec = shimmerSpec,
        )
    }

    @Test
    fun compareFastOutLinearInEasing() = runTest {
        val tweenSpec = tween<Float>(10_000, easing = FastOutLinearInEasing)
        val shimmerSpec = shimmerSpec(10_000, easing = FastOutLinearInEasing)

        compareSpecs(
            tweenSpec = tweenSpec,
            shimmerSpec = shimmerSpec,
        )
    }

    @Test
    fun assertDelayIsAppended() = runTest {
        val shimmerSpec = shimmerSpec(10_000, delayMillis = 5_000)
        val shimmerValues = mutableListOf<Float>()

        withContext(MonotonicTestClock()) {
            Animatable(0f).animateTo(
                targetValue = 1f,
                animationSpec = shimmerSpec,
            ) {
                shimmerValues.add(value)
            }
        }

        shimmerValues.takeLast(6).forEach { actual ->
            assertEquals(expected = 1f, actual)
        }
    }

    @Test
    fun compareLinearEasing() = runTest {
        val tweenSpec = tween<Float>(10_000, easing = LinearEasing)
        val shimmerSpec = shimmerSpec(10_000, easing = LinearEasing)

        compareSpecs(
            tweenSpec = tweenSpec,
            shimmerSpec = shimmerSpec,
        )
    }

    private suspend fun compareSpecs(
        tweenSpec: TweenSpec<Float>,
        shimmerSpec: KeyframesSpec<Float>,
    ) {
        val expectedValues = mutableListOf<Float>()
        val actualValues = mutableListOf<Float>()

        val animatable = Animatable(0f)
        withContext(MonotonicTestClock()) {
            animatable.animateTo(
                targetValue = 1f,
                animationSpec = tweenSpec,
            ) {
                expectedValues.add(value)
            }
            animatable.animateTo(
                targetValue = 1f,
                animationSpec = shimmerSpec,
            ) {
                actualValues.add(value)
            }
        }

        expectedValues.forEachIndexed { index, expectedValue ->
            assertEquals(expectedValue, actualValues[index], absoluteTolerance = 0.0005f)
        }
    }
}

/**
 * Test clock that advances the frame time by 1 second, each time it is called.
 */
private class MonotonicTestClock : MonotonicFrameClock {
    private var currentNanos = 0L

    override suspend fun <R> withFrameNanos(onFrame: (frameTimeNanos: Long) -> R): R {
        currentNanos += 1_000_000_000
        return onFrame(currentNanos)
    }
}
