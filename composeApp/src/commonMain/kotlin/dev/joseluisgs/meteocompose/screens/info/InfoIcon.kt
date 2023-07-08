package dev.joseluisgs.meteocompose.screens.info

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.unit.dp
import dev.joseluisgs.meteocompose.Res
import io.github.skeptick.libres.compose.painterResource

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun InfoIcon() {
    val painter = painterResource(Res.image.jlgs)
    val openDialog = remember { mutableStateOf(false) }
    IconButton(onClick = {
        openDialog.value = true
    }) {
        Image(
            painter = painter,
            contentDescription = "App Icon",
            modifier = Modifier
                .clip(CircleShape)
                .size(96.dp)
                .pointerHoverIcon(PointerIcon.Hand)

        )
    }
    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = { openDialog.value = false },
            title = { Text(text = "Alerta") },
            text = { Text(text = "Este soy yo, y ya has visto como funciona una alerta") },
            confirmButton = {
                Button(onClick = { openDialog.value = false }) {
                    Text(text = "Aceptar")
                }
            })
    }

}
