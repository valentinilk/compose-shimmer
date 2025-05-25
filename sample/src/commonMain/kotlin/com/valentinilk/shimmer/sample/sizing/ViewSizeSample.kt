package com.valentinilk.shimmer.sample.sizing

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
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.valentinilk.shimmer.sample.SampleColumn
import com.valentinilk.shimmer.shimmer

@Composable
fun ViewSizeSample() {
    SampleColumn(
        title = "View Sized",
    ) {
        ShimmerOnParentSample()
        ShimmerOnChildrenSample()
    }
}

@Composable
private fun ShimmerOnParentSample() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(112.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .shimmer(),
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
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        text = "Shimmer on Parent",
        textAlign = TextAlign.Center,
    )
}

@Composable
private fun ShimmerOnChildrenSample() {
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
            Box(
                modifier = Modifier
                    .size(80.dp, 80.dp)
                    .shimmer()
                    .background(Color.LightGray),
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(24.dp)
                        .shimmer()
                        .background(Color.LightGray),
                )
                Box(
                    modifier = Modifier
                        .size(120.dp, 20.dp)
                        .shimmer()
                        .background(Color.LightGray),
                )
            }
        }
    }
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        text = "Shimmer on Child Views",
        textAlign = TextAlign.Center,
    )
}
