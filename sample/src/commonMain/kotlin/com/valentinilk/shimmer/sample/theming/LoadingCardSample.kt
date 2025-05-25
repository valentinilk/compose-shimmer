package com.valentinilk.shimmer.sample.theming

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Downloading
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp

@Composable
fun LoadingCardSample() {
    ThemingSampleCard {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(245, 245, 245)),
            contentAlignment = Alignment.Center,
        ) {
            Column(
                // This example uses a custom modifier defined in CustomModifierSample.kt
                modifier = Modifier.shimmer(duration = 600),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Image(
                    modifier = Modifier.size(64.dp),
                    imageVector = Icons.Outlined.Downloading,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                )
                Text("LOADING", style = MaterialTheme.typography.headlineMedium)
            }
        }
    }
}
