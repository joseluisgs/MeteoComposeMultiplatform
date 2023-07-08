package dev.joseluisgs.meteocompose.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.seiko.imageloader.rememberImagePainter
import dev.joseluisgs.meteocompose.Res
import dev.joseluisgs.meteocompose.utils.getPlatformName
import io.github.skeptick.libres.compose.painterResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(goToInfo: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }


    // Scaffold, contenido de la pantalla
    Scaffold(
        topBar = { HomeTopBar(goToInfo = goToInfo) },
    ) { padding ->
        Text(
            text = "Home",
            modifier = Modifier.padding(padding)
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(goToInfo: () -> Unit) {
    // TopBar
    TopAppBar(
        title = { Text(text = " ${Res.string.app_name} ${getPlatformName()}") },
        actions = {
            IconButton(onClick = { goToInfo() }) {
                Icon(imageVector = Icons.Default.Info, contentDescription = "Info")
            }
        },
        // colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
    )
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

