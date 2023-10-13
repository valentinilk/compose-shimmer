package com.valentinilk.shimmer.sample.sizing

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.valentinilk.shimmer.Shimmer
import com.valentinilk.shimmer.ShimmerBounds
import com.valentinilk.shimmer.rememberShimmer
import com.valentinilk.shimmer.sample.SampleColumn
import com.valentinilk.shimmer.shimmer

@Composable
fun WindowSizeSample() {
    SampleColumn(
        title = "Window Sized",
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            val shimmer = rememberShimmer(shimmerBounds = ShimmerBounds.Window)
            SharedShimmerSample(shimmer)
            NonShimmeringCard()
            SharedShimmerSample(shimmer)
        }
    }
}

@Composable
fun SharedShimmerSample(shimmer: Shimmer) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(112.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .shimmer(customShimmer = shimmer),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Box(
                modifier = Modifier
                    .size(80.dp, 80.dp)
                    .background(Color.LightGray),
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(24.dp)
                        .background(Color.LightGray),
                )
                Box(
                    modifier = Modifier
                        .size(120.dp, 20.dp)
                        .background(Color.LightGray),
                )
            }
        }
    }
}

@Composable
fun NonShimmeringCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(112.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Image(
                modifier = Modifier
                    .size(80.dp, 80.dp)
                    .background(Color.Blue.copy(alpha = 0.1f)),
                imageVector = Icons.Filled.Person,
                contentDescription = null,
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Text(
                    text = "Some Headline",
                    style = MaterialTheme.typography.h6,
                )
                Text(
                    text = "Content",
                    style = MaterialTheme.typography.body1,
                )
            }
        }
    }
}
