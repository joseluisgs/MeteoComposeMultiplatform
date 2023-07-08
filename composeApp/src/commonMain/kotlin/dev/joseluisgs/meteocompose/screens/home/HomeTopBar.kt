package dev.joseluisgs.meteocompose.screens.home

import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import dev.joseluisgs.meteocompose.Res
import dev.joseluisgs.meteocompose.utils.getPlatformName

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