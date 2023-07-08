package dev.joseluisgs.meteocompose.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.seiko.imageloader.rememberImagePainter
import dev.joseluisgs.meteocompose.Res
import dev.joseluisgs.meteocompose.utils.getPlatformName
import dev.joseluisgs.meteocompose.utils.openUrl
import org.jetbrains.compose.resources.painterResource

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

        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {

            Text(
                text = "Login",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(16.dp)
            )

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth().padding(16.dp)
            )

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                singleLine = true,
                visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password
                ),
                trailingIcon = {
                    IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                        val imageVector = if (passwordVisibility) Icons.Default.Close else Icons.Default.Edit
                        Icon(
                            imageVector,
                            contentDescription = if (passwordVisibility) "Hide password" else "Show password"
                        )
                    }
                }
            )

            Button(
                onClick = { /* Handle login logic here */ },
                modifier = Modifier.fillMaxWidth().padding(16.dp)
            ) {
                Text("Login")
            }

            TextButton(
                onClick = { openUrl("https://github.com/terrakok") },
                modifier = Modifier.fillMaxWidth().padding(16.dp)
            ) {
                Text("Open github")
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                ImageExampleOnline()
                ImageExampleLocal()
            }
        }
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

