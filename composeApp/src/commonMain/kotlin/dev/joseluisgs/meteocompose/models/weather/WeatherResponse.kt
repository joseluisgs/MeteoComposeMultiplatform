package dev.joseluisgs.meteocompose.models.weather

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// POJOS para procesar los resultados de la API

@Serializable
data class WeatherResponse(
    val location: Location,
    val current: Current,
    val forecast: Forecast
)

@Serializable
data class Location(
    @SerialName("name")
    val name: String,
    @SerialName("region")
    val region: String,
    @SerialName("country")
    val country: String,
    @SerialName("lat")
    val lat: Double,
    @SerialName("lon")
    val lon: Double,
    @SerialName("tz_id")
    val tzId: String,
    @SerialName("localtime")
    val localtime: String
)

@Serializable
data class Current(
    @SerialName("temp_c") val tempC: Double,
    val condition: Condition,
    @SerialName("feelslike_c") val feelslikeC: Double,
    @SerialName("humidity") val humidity: Int,
    @SerialName("wind_kph") val windKph: Int,
    @SerialName("precip_mm") val precipMm: Int,
    @SerialName("vis_km") val visKm: Double,
    @SerialName("uv") val uv: Double,
)

@Serializable
data class Condition(
    val text: String,
    val icon: String
)

@Serializable
data class Forecast(
    val forecastday: List<Forecastday>
)

@Serializable
data class Forecastday(
    val day: Day,
    val hour: List<Hour>
)

@Serializable
data class Day(
    @SerialName("avgtemp_c") val avgtempC: Double,
    @SerialName("maxtemp_c") val maxtempC: Double,
    @SerialName("mintemp_c") val mintempC: Double,
    val condition: Condition,
)

@Serializable
data class Hour(
    @SerialName("temp_c") val tempC: Double,
    @SerialName("feelslike_c") val feelslikeC: Double,
    @SerialName("chance_of_rain") val chanceOfRain: Int,
)
