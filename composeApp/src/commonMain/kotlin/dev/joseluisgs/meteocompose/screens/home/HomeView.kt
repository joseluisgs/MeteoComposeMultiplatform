package dev.joseluisgs.meteocompose.screens.home

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(vm: HomeViewModel, goToInfo: () -> Unit) {
    // Scaffold, contenido de la pantalla
    Scaffold(
        topBar = { HomeTopBar(goToInfo = goToInfo) },
    ) { padding ->
        HomeContent(padding, isLoading = vm.state.isLoading)
    }
}

