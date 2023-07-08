package dev.joseluisgs.meteocompose.screens.info

import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import dev.joseluisgs.meteocompose.Res

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InfoTopBar(goBack: () -> Unit) {
    // TopBar
    TopAppBar(
        title = { Text(text = "${Res.string.app_name} ${Res.string.info_screen}") },
        navigationIcon = {
            IconButton(onClick = { goBack() }) {
                Icon(Icons.Filled.Close, contentDescription = "Close")
            }
        },
    )
}