import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import dev.joseluisgs.meteocompose.App
import dev.joseluisgs.meteocompose.Res
import dev.joseluisgs.meteocompose.di.appModule
import dev.joseluisgs.meteocompose.utils.getPlatformName
import org.koin.core.context.GlobalContext.startKoin


// Cliente de escritorio, lanzamos la aplicación
fun main() = application {
    // Importamos el módulo de Koin
    startKoin {
        printLogger()
        modules(appModule)
    }

    Window(
        title = "MeteoCompose ${getPlatformName()}",
        state = rememberWindowState(width = 800.dp, height = 600.dp),
        icon = painterResource(Res.image.app_icon),
        onCloseRequest = ::exitApplication,
    ) { App() }
}