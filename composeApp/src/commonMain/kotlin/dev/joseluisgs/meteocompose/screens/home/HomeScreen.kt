package dev.joseluisgs.meteocompose.screens.home

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import dev.joseluisgs.meteocompose.screens.info.InfoScreen
import org.lighthousegames.logging.logging

private val logger = logging()

object HomeScreen : Screen {

    init {
        logger.info { "Iniciando la Screen Home" }
    }

    @Composable
    override fun Content() {
        // Obtenemos el navigator
        val navigator = LocalNavigator.currentOrThrow
        HomeView(
            vm = rememberScreenModel { HomeViewModel() },
            goToInfo = {
                logger.debug { "Navigator: Home -> Info" }
                navigator.push(InfoScreen)
            }
        )
    }
}

