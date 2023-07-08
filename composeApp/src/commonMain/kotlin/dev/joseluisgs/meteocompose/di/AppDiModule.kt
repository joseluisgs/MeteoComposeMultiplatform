package dev.joseluisgs.meteocompose.di

import dev.joseluisgs.meteocompose.repository.DemoRepository
import dev.joseluisgs.meteocompose.screens.home.HomeViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val appModule = module {
    // single { DemoRepository() }
    // single { HomeViewModel(get()) }
    singleOf(::DemoRepository)
    singleOf(::HomeViewModel)
}