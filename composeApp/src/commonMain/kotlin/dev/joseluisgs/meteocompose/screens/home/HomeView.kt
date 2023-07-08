package dev.joseluisgs.meteocompose.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.lighthousegames.logging.logging

private val logger = logging()

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(vm: HomeViewModel, goToInfo: () -> Unit) {
    // Scaffold, contenido de la pantalla
    Scaffold(
        topBar = { HomeTopBar(goToInfo = goToInfo) },
    ) { padding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                HomeContent(
                    padding = padding,
                    isLoading = vm.state.isLoading
                )
            }
            // Aquí el snackba
            if (!vm.state.isLoading) {
                MySnackbar(
                    message = "Datos cargados correctamente",
                    actionLabel = "Recargar",
                    onDismiss = { logger.debug { "Snackbar dismissed" } },
                    onAction = { vm.loadData() },
                )
            }
        }
    }
}

@Composable
fun MySnackbar(
    message: String,
    actionLabel: String = "",
    duration: SnackbarDuration = SnackbarDuration.Short,
    onDismiss: () -> Unit = {},
    onAction: () -> Unit = {},
) {
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(snackbarHostState) {
        when (snackbarHostState.showSnackbar(message, actionLabel, duration = duration)) {
            SnackbarResult.Dismissed -> onDismiss()
            SnackbarResult.ActionPerformed -> onAction()
        }
    }

    SnackbarHost(hostState = snackbarHostState)
}

