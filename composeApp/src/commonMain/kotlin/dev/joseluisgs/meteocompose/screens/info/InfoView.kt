package dev.joseluisgs.meteocompose.screens.info

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.joseluisgs.meteocompose.utils.openUrl

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InfoView(goBack: () -> Unit) {

    // Scaffold, contenido de la pantalla
    Scaffold(
        topBar = { InfoTopBar(goBack = goBack) },
    ) {

        Column(modifier = Modifier.fillMaxSize()) {

            Text(
                text = "Información",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(16.dp)
            )

            TextButton(
                onClick = { openUrl("https://github.com/joseluisgs") },
                modifier = Modifier.fillMaxWidth().padding(16.dp)
            ) {
                Text("Abrir github")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InfoTopBar(goBack: () -> Unit) {
    // TopBar
    TopAppBar(
        title = { Text(text = "MeteoCompose: Información") },
        navigationIcon = {
            IconButton(onClick = { goBack() }) {
                Icon(Icons.Filled.Close, contentDescription = "Close")
            }
        },
    )
}
