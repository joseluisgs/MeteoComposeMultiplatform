package dev.joseluisgs.meteocompose.screens.info

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import dev.joseluisgs.meteocompose.Res
import io.github.skeptick.libres.compose.painterResource

@Composable
fun InfoIcon() {
    val painter = painterResource(Res.image.jlgs)
    Image(
        painter = painter,
        contentDescription = "App Icon",
        modifier = Modifier.clip(CircleShape).size(96.dp)
    )
}