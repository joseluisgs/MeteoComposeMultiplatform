package dev.joseluisgs.meteocompose.screens.home

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import org.lighthousegames.logging.logging

private val logger = logging()

object HomeScreen : Screen {

    init {
        logger.info { "Iniciando la Screen Home" }
    }

    @Composable
    override fun Content() {
        HomeView()
    }
}

