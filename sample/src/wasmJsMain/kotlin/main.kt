@file:OptIn(ExperimentalComposeUiApi::class)

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.CanvasBasedWindow
import com.valentinilk.shimmer.sample.AppBox

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    CanvasBasedWindow("shimmer") {
        AppBox()
    }
}
