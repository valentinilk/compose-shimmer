@file:OptIn(ExperimentalComposeUiApi::class)

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import com.valentinilk.shimmer.sample.AppBox

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    ComposeViewport {
        AppBox()
    }
}
