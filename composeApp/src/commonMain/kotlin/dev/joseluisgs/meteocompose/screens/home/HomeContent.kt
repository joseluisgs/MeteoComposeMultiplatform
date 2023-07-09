package dev.joseluisgs.meteocompose.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.seiko.imageloader.rememberImagePainter
import dev.joseluisgs.meteocompose.Res
import dev.joseluisgs.meteocompose.error.WeatherError
import dev.joseluisgs.meteocompose.models.weather.WeatherResult
import io.github.skeptick.libres.compose.painterResource

@Composable
fun HomeContent(state: HomeViewModel.State<WeatherResult, WeatherError>, padding: PaddingValues) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier.fillMaxWidth().padding(padding)
    ) {
        SearchCity()
        when (state) {
            is HomeViewModel.State.Loading -> LoadingDataIndicator()
            is HomeViewModel.State.Content -> println("Success")
            is HomeViewModel.State.Error -> ErrorMessage(error = state.error)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchCity() {
    var searchCity by remember { mutableStateOf("") }

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
            onClick = { /* TODO */ },
            shape = MaterialTheme.shapes.small,
            enabled = searchCity.trim().isNotEmpty(),
        ) {
            Icon(Icons.Outlined.Search, "Buscar")
        }
    }
}


@Composable
fun ImageExampleOnline() {
    val url = "https://pbs.twimg.com/profile_images/1164967571579396096/YXMN71A1_400x400.jpg"
    val painter = rememberImagePainter(url)
    Image(
        painter = painter,
        contentDescription = null,
        modifier = Modifier
            .padding(16.dp)
            .clip(CircleShape)
    )
}

@Composable
fun ImageExampleLocal() {
    val painter = painterResource(Res.image.jlgs)
    Image(
        painter = painter,
        contentDescription = null,
        modifier = Modifier
            .padding(16.dp)
            .clip(CircleShape)
    )
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
        Text(
            text = error.message,
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.error
        )
    }
}

