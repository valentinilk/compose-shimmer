package com.valentinilk.shimmer.sample

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.InfiniteRepeatableSpec
import androidx.compose.animation.core.TweenSpec
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.valentinilk.shimmer.LocalShimmerTheme
import com.valentinilk.shimmer.ShimmerTheme
import kotlin.math.roundToInt

@Composable
fun ThemeEditor(shimmerTheme: ShimmerTheme, onThemeChanged: (ShimmerTheme) -> Unit) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Theme Editor",
            style = MaterialTheme.typography.h5,
        )
        Text(
            text = "Changing a value will reset the shimmer.",
            style = MaterialTheme.typography.body1,
        )

        Labeled(label = "Shimmer Width: ${shimmerTheme.shimmerWidth.value.roundToInt()}") {
            Slider(
                value = shimmerTheme.shimmerWidth.value,
                onValueChange = {
                    onThemeChanged(shimmerTheme.copy(shimmerWidth = it.dp))
                },
                valueRange = 50f..1000f,
                colors = redSliderColors(),
            )
        }

        // Only supports the default specs
        val animationSpec = shimmerTheme.animationSpec as? InfiniteRepeatableSpec<Float>
        val tweenSpec = animationSpec?.animation as? TweenSpec<Float>
        if (animationSpec != null && tweenSpec != null) {
            AnimationSpecEditor(animationSpec, tweenSpec) { newSpec ->
                onThemeChanged(shimmerTheme.copy(animationSpec = newSpec))
            }
        }


        Labeled("Rotation: ${shimmerTheme.rotation.roundToInt()}") {
            Slider(
                value = shimmerTheme.rotation,
                onValueChange = {
                    onThemeChanged(shimmerTheme.copy(rotation = it))
                },
                valueRange = 0f..360f,
                colors = redSliderColors(),
            )
        }
    }
}

@Composable
private fun AnimationSpecEditor(
    animationSpec: InfiniteRepeatableSpec<Float>,
    tweenSpec: TweenSpec<Float>,
    onAnimationSpecChanged: (AnimationSpec<Float>) -> Unit
) {
    Labeled(label = "Duration: ${tweenSpec.durationMillis}") {
        Slider(
            value = tweenSpec.durationMillis.toFloat(),
            onValueChange = { duration ->
                val tweenCopy = tweenSpec.copy(durationMillis = duration.toInt())
                onAnimationSpecChanged(animationSpec.copy(animationSpec = tweenCopy))
            },
            valueRange = 250f..3000f,
            colors = redSliderColors(),
        )
    }

    Labeled(label = "Delay: ${tweenSpec.delay}") {
        Slider(
            value = tweenSpec.delay.toFloat(),
            onValueChange = { delay ->
                val tweenCopy = tweenSpec.copy(delayMillis = delay.toInt())
                onAnimationSpecChanged(animationSpec.copy(animationSpec = tweenCopy))
            },
            valueRange = 0f..3000f,
            colors = redSliderColors(),
        )
    }
}

@Composable
private fun Labeled(label: String, content: @Composable () -> Unit) {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(text = label, style = MaterialTheme.typography.subtitle2)
        Box(
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            content()
        }
    }
}

@Composable
private fun redSliderColors() = SliderDefaults.colors(
    thumbColor = Color.Red,
    activeTrackColor = Color.Red,
)

@Preview(showBackground = true)
@Composable
private fun Preview_ThemeEditor() {
    MaterialTheme {
        ThemeEditor(
            shimmerTheme = LocalShimmerTheme.current,
            onThemeChanged = {}
        )
    }
}
