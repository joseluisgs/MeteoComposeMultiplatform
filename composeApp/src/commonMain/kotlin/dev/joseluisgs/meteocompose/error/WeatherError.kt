package dev.joseluisgs.meteocompose.error

sealed class WeatherError(val message: String) {
    class NetworkError(message: String) : WeatherError(message)
}