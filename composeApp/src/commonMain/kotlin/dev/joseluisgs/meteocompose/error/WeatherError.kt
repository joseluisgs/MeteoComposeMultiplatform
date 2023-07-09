package dev.joseluisgs.meteocompose.error

sealed class WeatherError(val message: String) {
    class NetworkProblem(message: String) : WeatherError(message)
    class NotFound(message: String) : WeatherError(message)
}