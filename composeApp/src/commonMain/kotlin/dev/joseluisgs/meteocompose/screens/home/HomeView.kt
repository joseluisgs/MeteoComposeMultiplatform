package dev.joseluisgs.meteocompose.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.lighthousegames.logging.logging

private val logger = logging()

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(vm: HomeViewModel, goToInfo: () -> Unit) {
    // Scaffold, contenido de la pantalla
    var lastCity by remember { mutableStateOf("") }
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
                    state = vm.state,
                    onClickSearchCity = {
                        lastCity = it
                        vm.loadData(it)
                    },
                )
            }
            // Aquí el snackbar respecto al estado de error
            if (vm.state is HomeViewModel.State.Error) {
                logger.debug { "Error" }
                MySnackbar(
                    message = (vm.state as HomeViewModel.State.Error).error.message,
                    actionLabel = "Recargar",
                    onDismiss = { logger.debug { "Snackbar dismissed" } },
                    onAction = { vm.loadData(lastCity) },
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

