package com.valentinilk.shimmer.sample

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AspectRatio
import androidx.compose.material.icons.outlined.Crop
import androidx.compose.material.icons.outlined.FitScreen
import androidx.compose.material.icons.outlined.Palette
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.valentinilk.shimmer.sample.sizing.CustomSizeSample
import com.valentinilk.shimmer.sample.sizing.ViewSizeSample
import com.valentinilk.shimmer.sample.sizing.WindowSizeSample
import com.valentinilk.shimmer.sample.theming.ThemingSamples
import com.valentinilk.shimmer.sample.enabling.EnabledDisabledSample

enum class NavItems(
    val route: String,
    val icon: ImageVector,
    val title: String,
    val content: @Composable () -> Unit
) {
    ViewSizeSample(
        "viewSizeSample",
        Icons.Outlined.FitScreen,
        "View",
        { ViewSizeSample() }
    ),
    WindowSizeSample(
        "windowSizeSample",
        Icons.Outlined.AspectRatio,
        "Window",
        { WindowSizeSample() }
    ),
    CustomSizeSample(
        "customSizeSample",
        Icons.Outlined.Crop,
        "Custom",
        { CustomSizeSample() }
    ),
    ThemingSamples(
        "themingSamples",
        Icons.Outlined.Palette,
        "Theming",
        { ThemingSamples() }
    ),
    EnablingSamples(
        "enablingSamples",
        Icons.Outlined.Visibility,
        "Visibility",
        { EnabledDisabledSample() }
    )
}
