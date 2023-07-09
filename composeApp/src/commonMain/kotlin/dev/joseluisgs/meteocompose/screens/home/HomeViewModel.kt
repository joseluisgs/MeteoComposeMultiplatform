package dev.joseluisgs.meteocompose.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import com.github.michaelbull.result.mapBoth
import dev.joseluisgs.meteocompose.error.WeatherError
import dev.joseluisgs.meteocompose.models.weather.WeatherResult
import dev.joseluisgs.meteocompose.repository.WeatherRepository
import kotlinx.coroutines.launch
import org.lighthousegames.logging.logging

private val logger = logging()

class HomeViewModel(
    private val repository: WeatherRepository
) : ScreenModel {
    // Vamos a usar MutableState de Compose
    var state by mutableStateOf<State<WeatherResult, WeatherError>>(State.Empty)
        private set

    init {
        logger.info { "Init HomeViewModel" }
    }

    fun loadData(city: String = "Madrid") {
        logger.debug { "Load weather info fro $city" }
        coroutineScope.launch {
            state = State.Loading
            repository.weatherForCity(city).mapBoth(
                success = { weatherResult ->
                    state = State.Content(weatherResult)
                },
                failure = { weatherError ->
                    state = State.Error(weatherError)
                }
            )
        }
    }

    // El estado de la vista
    sealed class State<out WeatherResult, out WeatherError> {
        object Loading : State<Nothing, Nothing>()
        data class Content<WeatherResult>(val data: WeatherResult) : State<WeatherResult, Nothing>()
        data class Error(val error: WeatherError) : State<Nothing, WeatherError>()
        object Empty : State<Nothing, Nothing>()
    }

}