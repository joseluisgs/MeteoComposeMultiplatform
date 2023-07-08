package dev.joseluisgs.meteocompose.screens.info

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InfoView(goBack: () -> Unit) {

    // Scaffold, contenido de la pantalla
    Scaffold(
        topBar = { InfoTopBar(goBack = goBack) },
    ) {
        InfoContent()
    }
}

@Composable
fun InfoContent() {
    Column(modifier = Modifier.fillMaxSize()) {
        Text("Hola")
        Text("Mundo")
        Text("MeteoCompose")
        Text("Versión: 1.0.0")
        Text("Autor: José Luis González Sánchez")
        Text("Licencia: MIT")
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
