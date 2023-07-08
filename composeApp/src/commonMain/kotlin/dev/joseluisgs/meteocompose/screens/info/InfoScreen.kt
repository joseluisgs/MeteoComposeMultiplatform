package dev.joseluisgs.meteocompose.screens.info

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.lighthousegames.logging.logging

private val logger = logging()

object InfoScreen : Screen {

    init {
        logger.info { "Iniciando la Screen Info" }
    }

    @Composable
    override fun Content() {
        // Recuperamos el navigator
        val navigator = LocalNavigator.currentOrThrow
        InfoView(
            goBack = {
                logger.debug { "Navigator: Info -> Home" }
                navigator.pop()
            }
        )
    }
}

