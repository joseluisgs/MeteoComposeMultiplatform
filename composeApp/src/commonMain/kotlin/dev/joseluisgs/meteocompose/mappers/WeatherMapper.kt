package dev.joseluisgs.meteocompose.mappers

import dev.joseluisgs.meteocompose.models.weather.*

// Lo hacemos con una función de extensión
fun WeatherResponse.toResult(): WeatherResult {
    val location = extractCurrentLocationWeatherFrom(this)
    val current = extractCurrentWeatherFrom(this)
    val forecast = extractForecastWeatherFrom(this)

    return WeatherResult(
        location = location,
        currentWeather = current,
        forecast = forecast,
    )
}

fun extractCurrentLocationWeatherFrom(weatherResponse: WeatherResponse): LocationInfo {
    return LocationInfo(
        name = weatherResponse.location.name,
        country = weatherResponse.location.country,
        region = weatherResponse.location.region,
        lat = weatherResponse.location.lat,
        lon = weatherResponse.location.lon,
        tzId = weatherResponse.location.tzId,
        localtime = weatherResponse.location.localtime,
    )
}


private fun extractCurrentWeatherFrom(response: WeatherResponse): WeatherInfo {
    return WeatherInfo(
        condition = response.current.condition.text,
        iconUrl = "https:" + response.current.condition.icon.replace("64x64", "128x128"),
        temperature = response.current.tempC,
        feelsLike = response.current.feelslikeC,
    )
}

private fun extractForecastWeatherFrom(response: WeatherResponse): List<WeatherInfo> {
    return response.forecast.forecastday.map { forecastDay ->
        WeatherInfo(
            condition = forecastDay.day.condition.text,
            iconUrl = "https:" + forecastDay.day.condition.icon,
            temperature = forecastDay.day.avgtempC,
            feelsLike = avgFeelsLike(forecastDay),
            chanceOfRain = avgChanceOfRain(forecastDay),
        )
    }
}

private fun avgFeelsLike(forecastDay: Forecastday): Double =
    forecastDay.hour.map(Hour::feelslikeC).average()

private fun avgChanceOfRain(forecastDay: Forecastday): Double =
    forecastDay.hour.map(Hour::chanceOfRain).average()

