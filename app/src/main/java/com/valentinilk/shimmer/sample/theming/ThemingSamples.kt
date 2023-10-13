package com.valentinilk.shimmer.sample.theming

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ThemingSamples() {
    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 32.dp, vertical = 20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        LoadingCardSample()
        NotificationCardSample()
        CreditCardSample()
        LakeCardSample()
        MandalaCardSample()
        SkullCardSample()
    }
}

@Composable
fun ThemingSampleCard(content: @Composable () -> Unit) = Card(
    modifier = Modifier
        .fillMaxWidth()
        .height(210.dp),
    content = content,
    shape = RoundedCornerShape(CornerSize(12.dp)),
)
