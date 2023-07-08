package dev.joseluisgs.meteocompose.screens.info

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.IconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun InfoText() {
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

