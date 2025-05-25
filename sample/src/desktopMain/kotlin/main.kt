import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.valentinilk.shimmer.sample.AppBox
import java.awt.Dimension

fun main() {
    application {
        Window(
            title = "Shimmer",
            state = rememberWindowState(width = 480.dp, height = 840.dp),
            onCloseRequest = ::exitApplication,
        ) {
            window.minimumSize = Dimension(350, 600)
            AppBox()
        }
    }
}
