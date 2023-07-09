package dev.joseluisgs.meteocompose.repository

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import com.github.michaelbull.result.mapBoth
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

    private suspend fun getWeatherForCity(city: String): Result<WeatherResponse, WeatherError> {
        logger.debug { "getWeatherForCity: $city" }

        val res = weatherRest.client.get(
            "https://api.weatherapi.com/v1/forecast.json" +
                    "?key=$apiKey" +
                    "&q=$city" +
                    "&days=5" +
                    "&aqi=no" +
                    "&alerts=no"
        )
        return if (res.status.value in 400..499) {
            Err(WeatherError.NetworkProblem("La ciudad $city no existe en el servicio de clima"))
        } else if (res.status.value >= 500) {
            Err(WeatherError.NetworkProblem("Error al consultar el servicio de clima"))
        } else {
            Ok(res.body<WeatherResponse>())
        }
    }

    suspend fun weatherForCity(city: String): Result<WeatherResult, WeatherError> {
        logger.debug { "weatherForCity: $city" }
        return try {
            getWeatherForCity(city).mapBoth(
                success = { weatherResponse ->
                    Ok(weatherResponse.toResult())
                },
                failure = { error ->
                    Err(error)
                })
        } catch (e: Exception) {
            e.printStackTrace()
            logger.error { "weatherForCity: ${e.localizedMessage}" }
            Err(WeatherError.NetworkProblem("Error al consultar el servicio de clima"))
        }
    }
}
