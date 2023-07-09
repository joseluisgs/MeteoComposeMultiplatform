package dev.joseluisgs.meteocompose.models.weather

// POJOS para procesar los datos en la app
data class LocationInfo(
    val name: String,
    val region: String,
    val country: String,
    val localtime: String,
    val lat: Double,
    val lon: Double,
    val tzId: String,
)

data class WeatherInfo(
    val condition: String,
    val iconUrl: String,
    val temperature: Double,
    val feelsLike: Double,
    val chanceOfRain: Double? = null,
    val humidity: Double? = null,
    val wind: Double? = null,
    val precipitation: Double? = null,
    val visibility: Double? = null,
    val uvIndex: Double? = null,
)

data class WeatherResult(
    val location: LocationInfo,
    val currentWeather: WeatherInfo,
    val forecast: List<WeatherInfo>,
)
