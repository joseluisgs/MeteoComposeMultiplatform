package dev.joseluisgs.meteocompose.repository

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import dev.joseluisgs.meteocompose.Res
import dev.joseluisgs.meteocompose.api.WeatherRest
import dev.joseluisgs.meteocompose.error.WeatherError
import dev.joseluisgs.meteocompose.mappers.toResult
import dev.joseluisgs.meteocompose.models.weather.WeatherResponse
import dev.joseluisgs.meteocompose.models.weather.WeatherResult
import io.ktor.client.call.*
import io.ktor.client.request.*
import org.lighthousegames.logging.logging

private val logger = logging()

class WeatherRepository(private val weatherRest: WeatherRest) {
    private val apiKey = Res.string.api_key

    init {
        logger.info { "Init WeatherRepository" }
    }

    private suspend fun getWeatherForCity(city: String): WeatherResponse {
        logger.debug { "getWeatherForCity: $city" }

        return weatherRest.client.get(
            "https://api.weatherapi.com/v1/forecast.json" +
                    "?key=$apiKey" +
                    "&q=$city" +
                    "&days=5" +
                    "&aqi=no" +
                    "&alerts=no"
        ).body<WeatherResponse>()
    }

    suspend fun weatherForCity(city: String): Result<WeatherResult, WeatherError> {
        logger.debug { "weatherForCity: $city" }

        return try {
            val response = getWeatherForCity(city)
            val content = response.toResult()
            Ok(content)
        } catch (e: Exception) {
            e.printStackTrace()
            logger.error { "weatherForCity: ${e.localizedMessage}" }
            Err(WeatherError.NetworkError(e.localizedMessage))
        }
    }
}
