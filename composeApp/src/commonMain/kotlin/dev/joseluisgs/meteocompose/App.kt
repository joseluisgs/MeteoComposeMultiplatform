package dev.joseluisgs.meteocompose

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import dev.joseluisgs.meteocompose.screens.home.HomeScreen
import dev.joseluisgs.meteocompose.theme.AppTheme
import org.lighthousegames.logging.logging

private val logger = logging()

// clase principal, la tener AppTheme, ya tenemos el tema de la app
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App() = AppTheme {
    logger.info { "Iniciando la App: ${Res.string.app_name}" }
    // Inicializamos el navegador con la pantalla principal
    Navigator(HomeScreen)
}
