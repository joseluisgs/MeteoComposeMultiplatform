import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import dev.joseluisgs.meteocompose.App


// Cliente de escritorio, lanzamos la aplicación
fun main() = application {
    Window(
        title = "MeteoCompose Desktop",
        state = rememberWindowState(width = 800.dp, height = 600.dp),
        icon = painterResource("app-icon.png"),
        onCloseRequest = ::exitApplication,
    ) { App() }
}