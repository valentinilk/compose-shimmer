package com.valentinilk.shimmer.sample.sizing

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.dp
import com.valentinilk.shimmer.ShimmerBounds
import com.valentinilk.shimmer.unclippedBoundsInWindow
import com.valentinilk.shimmer.rememberShimmer
import com.valentinilk.shimmer.sample.SampleColumn

@Composable
fun CustomSizeSample() {
    SampleColumn(
        title = "Custom Sized",
    ) {
        val shimmer = rememberShimmer(ShimmerBounds.Custom)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { layoutCoordinates ->
                    val position = layoutCoordinates.unclippedBoundsInWindow()
                    shimmer.updateBounds(position)
                },
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            SharedShimmerSample(shimmer)
            NonShimmeringCard()
            SharedShimmerSample(shimmer)
        }
    }
}
