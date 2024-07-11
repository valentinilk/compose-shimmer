package com.valentinilk.shimmer

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.node.DrawModifierNode
import androidx.compose.ui.node.GlobalPositionAwareModifierNode
import androidx.compose.ui.node.ModifierNodeElement
import androidx.compose.ui.platform.LocalDensity

@Composable
fun Modifier.shimmer(
    customShimmer: Shimmer = rememberShimmer(ShimmerBounds.View),
): Modifier {
    val width = with(LocalDensity.current) { customShimmer.theme.shimmerWidth.toPx() }
    val area = remember(width, customShimmer.theme.rotation) {
        ShimmerArea(width, customShimmer.theme.rotation)
    }
    LaunchedEffect(area, customShimmer) {
        customShimmer.boundsFlow.collect {
            area.updateBounds(it)
        }
    }
    return this then ShimmerElement(area, customShimmer.effect)
}

private data class ShimmerElement(
    var area: ShimmerArea,
    var effect: ShimmerEffect,
) : ModifierNodeElement<ShimmerNode>() {

    override fun create(): ShimmerNode = ShimmerNode(area, effect)

    override fun update(node: ShimmerNode) {
        node.area = area
        node.effect = effect
    }
}

private class ShimmerNode(
    var area: ShimmerArea,
    var effect: ShimmerEffect,
) : Modifier.Node(),
    DrawModifierNode,
    GlobalPositionAwareModifierNode {

    override fun ContentDrawScope.draw() {
        with(effect) { draw(area) }
    }

    override fun onGloballyPositioned(coordinates: LayoutCoordinates) {
        val viewBounds = coordinates.unclippedBoundsInWindow()
        area.viewBounds = viewBounds
    }
}
