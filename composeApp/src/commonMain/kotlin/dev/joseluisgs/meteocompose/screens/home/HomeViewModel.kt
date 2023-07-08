package dev.joseluisgs.meteocompose.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import dev.joseluisgs.meteocompose.repository.DemoRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.lighthousegames.logging.logging

private val logger = logging()

class HomeViewModel(
    private val repository: DemoRepository
) : ScreenModel {
    // Vamos a usar MutableState de Compose
    var state by mutableStateOf(UiState())
        private set

    init {
        logger.info { "Init HomeViewModel" }
        logger.warn { repository.getDemoData() }
        // Lanzamos la carga de datos
        loadData()
    }

    fun loadData() {
        coroutineScope.launch {
            state = state.copy(isLoading = true)
            delay(2000)
            state = state.copy(isLoading = false)
        }
    }

    // El estado de la vista
    data class UiState(
        val isLoading: Boolean = false,
    )
}