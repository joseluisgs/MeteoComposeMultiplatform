package dev.joseluisgs.meteocompose.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.seiko.imageloader.rememberImagePainter
import dev.joseluisgs.meteocompose.error.WeatherError
import dev.joseluisgs.meteocompose.models.weather.WeatherInfo
import dev.joseluisgs.meteocompose.models.weather.WeatherResult

@Composable
fun HomeContent(
    state: HomeViewModel.State<WeatherResult, WeatherError>,
    onClickSearchCity: (String) -> Unit,
    padding: PaddingValues
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier.fillMaxWidth()
            .padding(padding)
            .verticalScroll(rememberScrollState())
    ) {
        SearchCity(onClickSearchCity = onClickSearchCity)
        when (state) {
            is HomeViewModel.State.Loading -> LoadingDataIndicator()
            is HomeViewModel.State.Content -> ContentInfo(data = state.data)
            is HomeViewModel.State.Error -> ErrorMessage(error = state.error)
            HomeViewModel.State.Empty -> {}
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchCity(onClickSearchCity: (String) -> Unit) {
    var searchCity by remember { mutableStateOf("cazorla") }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        OutlinedTextField(
            value = searchCity,
            onValueChange = { searchCity = it },
            modifier = Modifier.padding(end = 16.dp).weight(1f), // Peso 1
            placeholder = { Text("Ciudad...") },
            label = { Text(text = "Buscar por lugar") },
            leadingIcon = { Icon(Icons.Filled.LocationOn, "Location") },
        )
        Button(
            onClick = { onClickSearchCity(searchCity) },
            shape = MaterialTheme.shapes.small,
            enabled = searchCity.trim().isNotEmpty(),
        ) {
            Icon(Icons.Outlined.Search, "Buscar")
        }
    }
}

@Composable
fun ContentInfo(data: WeatherResult) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Información Meteorológica: ${data.location.name}",
            modifier = Modifier.padding(all = 8.dp),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary
        )

        CityWeatherInfo(data)

        Divider(
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(all = 16.dp),
        )
        CityWeatherPrevision(data.forecast)

    }

}


@Composable
private fun CityWeatherInfo(
    data: WeatherResult,
) {
    val imageState = rememberImagePainter(data.currentWeather.iconUrl)
    Card(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = data.currentWeather.condition,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.secondary
            )
            Image(
                painter = imageState,
                contentDescription = data.currentWeather.condition,
                modifier = Modifier.defaultMinSize(minWidth = 128.dp, minHeight = 128.dp)
            )
            Text(
                text = "Temperatura: ${data.currentWeather.temperature} °C",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.secondary
            )
            Row(
                modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically

            ) {
                Text(
                    text = "Sensación: ${data.currentWeather.feelsLike} °C",
                    style = MaterialTheme.typography.labelSmall
                )
                Text(
                    text = "Humedad: ${data.currentWeather.humidity} %",
                    style = MaterialTheme.typography.labelSmall
                )
                Text(
                    text = "Viento: ${data.currentWeather.wind} km/h",
                    style = MaterialTheme.typography.labelSmall
                )
                Text(
                    text = "Precipitación: ${data.currentWeather.precipitation} mm",
                    style = MaterialTheme.typography.labelSmall
                )
            }
        }
    }
}

@Composable
private fun CityWeatherPrevision(forecast: List<WeatherInfo>) {
    Text(
        text = "Previsión para los ${forecast.size} próximos días:",
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.secondary
    )
    LazyRow(
        modifier = Modifier.padding(all = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(forecast) { weatherInfo ->
            ForecastWeatherInfo(weatherInfo)
        }
    }
}


@Composable
fun ForecastWeatherInfo(data: WeatherInfo) {
    val imageState = rememberImagePainter(data.iconUrl)
    Card(modifier = Modifier.padding(all = 4.dp)) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = data.condition,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.secondary
            )
            Image(
                painter = imageState,
                contentDescription = data.condition,
                modifier = Modifier.defaultMinSize(minWidth = 64.dp, minHeight = 64.dp)
            )
            Text(
                text = "Temperatura: ${data.temperature} °C",
                style = MaterialTheme.typography.labelSmall,
            )
            Text(
                text = "Prob: ${data.chanceOfRain} %",
                style = MaterialTheme.typography.labelSmall,
            )
        }
    }
}

@Composable
fun LoadingDataIndicator() {
    Box(modifier = Modifier.fillMaxSize()) {
        CircularProgressIndicator(
            modifier = Modifier
                .align(alignment = Alignment.Center)
                .defaultMinSize(minWidth = 96.dp, minHeight = 96.dp)
        )
    }
}

@Composable
fun ErrorMessage(error: WeatherError) {
    Column(
        modifier = Modifier.fillMaxSize()
            .padding(horizontal = 72.dp, vertical = 72.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Algo ha ido mal, prueba otra vez otra vez. ¯\\_(ツ)_/¯",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.error,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        /* Text(
             text = error.message,
             style = MaterialTheme.typography.titleSmall,
             color = MaterialTheme.colorScheme.error
         )*/
    }
}

