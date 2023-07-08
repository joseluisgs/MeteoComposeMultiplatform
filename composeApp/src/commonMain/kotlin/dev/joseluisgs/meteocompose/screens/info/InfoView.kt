package dev.joseluisgs.meteocompose.screens.info

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InfoView(goBack: () -> Unit) {

    // Scaffold, contenido de la pantalla
    Scaffold(
        topBar = { InfoTopBar(goBack = goBack) },
    ) { padding ->
        InfoContent(padding)
    }
}

@Composable
fun InfoContent(padding: PaddingValues) {
    // Hacemos un card con el contenido
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(padding).fillMaxSize()
    ) {
        // Card
        Card(
            elevation = CardDefaults.cardElevation(8.dp),
            shape = MaterialTheme.shapes.medium,
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 32.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally)
            ) {
                // Icono de la App
                InfoIcon()
                // Texto de la App
                InfoText()
            }

        }


    }
}

