package dev.joseluisgs.meteocompose.screens.info

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.unit.dp
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Brands
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.brands.Github
import compose.icons.fontawesomeicons.solid.AddressCard
import dev.joseluisgs.meteocompose.Res
import dev.joseluisgs.meteocompose.utils.getPlatformName
import dev.joseluisgs.meteocompose.utils.openUrl
import io.github.skeptick.libres.compose.painterResource

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
                IconInfoApp()
                // Texto de la App
                TextInfoApp()
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


@Composable
fun IconInfoApp() {
    val painter = painterResource(Res.image.jlgs)
    Image(
        painter = painter,
        contentDescription = "App Icon",
        modifier = Modifier.clip(CircleShape).size(96.dp)
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TextInfoApp() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = Res.string.app_name,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = MaterialTheme.typography.titleLarge.fontWeight
        )
        Text(
            text = getPlatformName(),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = MaterialTheme.typography.titleMedium.fontWeight
        )
        Text(
            text = "Versión: ${Res.string.app_version}",
            style = MaterialTheme.typography.titleSmall
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = Res.string.app_author,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.secondary,
            fontWeight = MaterialTheme.typography.bodyLarge.fontWeight
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            IconButton(
                onClick = {
                    openUrl(Res.string.app_author_web)
                }
            ) {
                Icon(
                    imageVector = FontAwesomeIcons.Solid.AddressCard,
                    contentDescription = "Website",
                    modifier = Modifier.size(16.dp).pointerHoverIcon(PointerIcon.Hand)
                )
            }
            IconButton(
                onClick = {
                    openUrl(Res.string.app_author_github)
                }
            ) {
                Icon(
                    imageVector = FontAwesomeIcons.Brands.Github,
                    contentDescription = "Github",
                    modifier = Modifier.size(16.dp).pointerHoverIcon(PointerIcon.Hand)
                )
            }
        }
    }
}

