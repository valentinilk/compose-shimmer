# Shimmer for Jetpack Compose

A library which offers a shimmering effect for Android's Jetpack Compose.

<img src="https://user-images.githubusercontent.com/1201850/131474443-620d0777-5b42-4914-839d-e6250b083538.gif" width="500" >

It was developed in need for a shimmer effect that traverses across the whole screen, bringing only a certain subset of child views to shine.

## Download

```
repositories {
  mavenCentral()
}

dependencies {
  implementation 'com.valentinilk.shimmer:compose-shimmer:1.0.3'
}
```

## Usage

The library provides a simple `shimmer()` modifier which can be applied like any other [modifier](https://developer.android.com/reference/kotlin/androidx/compose/ui/Modifier) in Compose as well.

As usual, the order of the modifiers matters. Every visual defined **after** the modifier will be affected by the shimmer. This includes child views and other modifiers as for example `background()`:

```
Box(
  modifier = Modifier
    .size(128.dp)
    .background(Color.Blue)
    .shimmer(),
  contentAlignment = Alignment.Center
) {
  Box(
    modifier = Modifier
      .size(64.dp)
      .background(Color.Red)
  )
}
```

<img src="https://user-images.githubusercontent.com/1201850/131474508-c572076c-d707-4ba1-9e84-729c1dc06f3f.gif" width="128" height="128">

```
Box(
  modifier = Modifier
    .size(128.dp)
    .shimmer()
    .background(Color.Blue),
  contentAlignment = Alignment.Center
) {
  Box(
    modifier = Modifier
      .size(64.dp)
      .background(Color.Red)
  )
}
```

<img src="https://user-images.githubusercontent.com/1201850/131474532-ce7d37c5-9af6-4577-82c2-737591f8c0c2.gif" width="128" height="128">

## Theming

The library includes a `ShimmerTheme` which can be provided as a [local composition](https://developer.android.com/reference/kotlin/androidx/compose/runtime/CompositionLocal). So overwriting the shimmer's default theme for the whole application is possible as usual:

```
val yourShimmerTheme = defaultShimmerTheme.copy(...)
CompositionLocalProvider(
  LocalShimmerTheme provides yourShimmerTheme
) {
  ...
}

```
As usual means the theme should be applied at the same level as for example the `MaterialTheme` is applied. There is no need to wrap every shimmer into a `CompositionLocalProvider`.

If it is more convenient, the theme can also be passed as a parameter by using the `rememberShimmer(...)` function, which is explained further down below.

The theme itself offers a few simple configurations like the shimmer's `rotation` or `width`. Additionally a few unabstracted objects like an `AnimationSpec` or `BlendMode` are exposed. While this violates the principales of [information hiding](https://en.wikipedia.org/wiki/Information_hiding) it allows for some great customizations.

For further information have a look at documentation in data class itself and have a look at the `ThemingSamples` in the sample app.

<img src="https://user-images.githubusercontent.com/1201850/131474645-a854a5ed-c390-4695-b4e4-e73224f2240c.gif" width="250" >

## Advanced Usage

The default `shimmer()` modifier creates a shimmering effect that will traverse over an area that is equal to the view's position and size. While this looks great for most cases, it just doesn't look as satisfiying for cases where the shimmer has to be applied to multiple views:

<img src="https://user-images.githubusercontent.com/1201850/131474721-29a57e04-139c-44b6-8721-5fb674706dc8.gif" width="250" >

Due to the differences in sizes, all three shimmers have a different velocitiy, which doesn't look as calm as it should. It would be way cleaner if it was only a single animation traversing over the views we want to be shimmering. 

That's why the library offers different options for the effect's boundaries:

```
val shimmerInstance = rememberShimmer(shimmerBounds = ShimmerBounds.XXX)
Box(modifier = Modifier.shimmer(shimmerInstance))
```

### ShimmerBounds.View

The default option, which was used to create the gifs above and is used in the theming samples in the app. Depending on the use case, this option might already be sufficient.

### ShimmerBounds.Window

One option is to use the boundaries of the current window. This will create a shimmer that travels over the whole window, while affecting only the views (and child views) which have the shimmer modifier attached.

Be aware that this option might look odd on scrollable content, because the shimmer will be positioned relative to the window and will not be moved together with the content. Depending on the theme this effect might be more or less visible. If the shimmer moves from the left to the right for example, and the content can only be scrolled up and down, this effect won't be visible and can be ignored.

```
Column {
  val shimmerInstance = rememberShimmer(shimmerBounds = ShimmerBounds.Window)
  Text("Shimmering Text", modifier = Modifier.shimmer(shimmerInstance))
  Text("Non-shimmering Text")
  Text("Shimmering Text", modifier = Modifier.shimmer(shimmerInstance))
}
```

<img src="https://user-images.githubusercontent.com/1201850/131474786-298be2fe-979a-4be4-b4ec-ef3c7d4026fa.gif" width="250" >

### ShimmerBounds.Custom

The downsides of the `Window` option is why the `ShimmerBounds.Custom` option exists. By using it the shimmer and its content will not be drawn until the bounds are set manually by using the `updateBounds` method on the `Shimmer`. This allows for attaching the shimmer to a scrollable list for example.

```
val shimmerInstance = rememberShimmer(ShimmerBounds.Custom)
Column(
  modifier = Modifier
    .fillMaxSize()
    .verticalScroll(rememberScrollState())
    .onGloballyPositioned { layoutCoordinates ->
      // Util function included in the library
      val position = layoutCoordinates.unclippedBoundsInWindow()
      shimmerInstance.updateBounds(position)
    },
) {
  Text("Shimmering Text", modifier = Modifier.shimmer(shimmerInstance))
  Text("Non-shimmering Text")
  Text("Shimmering Text", modifier = Modifier.shimmer(shimmerInstance))
}
```
Updating the bounds will not trigger a recomposition.

## Custom Modifier

It is also possible to create custom modifiers, if the default one is not convenient enough. One could, for example, create a modifier that takes in the animation duration as a parameter and creates the theming on the go. The whole code can be found in the `CustomModifierSample.kt` file in the sample app.

```
fun Modifier.shimmer(
    duration: Int
): Modifier = composed {
    val shimmer = rememberShimmer(
        shimmerBounds = ShimmerBounds.View,
        theme = createCustomTheme(duration),
    )
     shimmer(customShimmer = shimmer)
}
```

## License
```
Copyright 2021 Valentin Ilk

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
