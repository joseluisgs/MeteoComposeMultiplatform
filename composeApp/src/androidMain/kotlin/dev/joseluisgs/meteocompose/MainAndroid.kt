package dev.joseluisgs.meteocompose

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dev.joseluisgs.meteocompose.di.appModule
import dev.joseluisgs.meteocompose.utils.getPlatformName
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

// Cliente de Android, lanzamos la aplicación
class AndroidApp : Application() {
    companion object {
        lateinit var INSTANCE: AndroidApp
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this

        // Importamos el módulo de Koin
        startKoin {
            androidContext(this@AndroidApp)
            modules(appModule)
            androidLogger()
        }
    }
}

// Cliente de Android, lanzamos la actividad
class AppActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "MeteoCompose ${getPlatformName()}"
        setContent { App() }
    }
}

