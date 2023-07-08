package dev.joseluisgs.meteocompose.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.lighthousegames.logging.logging

private val logger = logging()

class HomeViewModel : ScreenModel {
    // Vamos a usar MutableState de Compose
    var state by mutableStateOf(UiState())
        private set

    init {
        logger.info { "Init HomeViewModel" }
        // Lanzamos la carga de datos
        coroutineScope.launch {
            state = state.copy(isLoading = true)
            loadData()
            state = state.copy(isLoading = false)
        }
    }

    private suspend fun loadData() {
        delay(2000)
    }

    // El estado de la vista
    data class UiState(
        val isLoading: Boolean = false,
    )
}