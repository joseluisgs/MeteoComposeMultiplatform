import org.jetbrains.compose.desktop.application.dsl.TargetFormat

// Gradle de Multiplataforma

plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.compose)
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.libres)
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    targetHierarchy.default()
    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

    jvm("desktop") {
        compilations.all {
            kotlinOptions {
                jvmTarget = "11"
            }
        }
    }

    sourceSets {
        all {
            languageSettings {
                optIn("org.jetbrains.compose.resources.ExperimentalResourceApi")
            }
        }

        // Dependencias comunes
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)
                implementation(compose.material3)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)
                implementation(compose.materialIconsExtended)
                // Voyager Navigator
                implementation(libs.voyager.navigator)
                // Coroutines Core
                implementation(libs.kotlinx.coroutines.core)
                // Ktor Core
                implementation(libs.ktor.core)
                // Serialization
                implementation(libs.kotlinx.serialization.json)
                // Koin
                implementation(libs.koin.core)
                // Libres
                api(libs.libres)
                // ImageLoader
                api(libs.composeImageLoader)
                // Logger
                api(libs.logger)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }

        // Dependencias de Android
        val androidMain by getting {
            dependencies {
                implementation(libs.androidx.appcompat)
                implementation(libs.androidx.activityCompose)
                implementation(libs.compose.uitooling)
                // Coroutines Android
                implementation(libs.kotlinx.coroutines.android)
                // Ktor Android
                implementation(libs.ktor.client.okhttp)
            }
        }

        // Dependencias de Desktop
        val desktopMain by getting {
            dependencies {
                implementation(compose.desktop.common)
                implementation(compose.desktop.currentOs)
                // Ktor Desktop
                implementation(libs.ktor.client.okhttp)
                // Logback para JVM
                implementation(libs.logback)
            }
        }

    }
}

// Configuración de Android
android {
    namespace = "dev.joseluisgs.meteocompose"
    compileSdk = 33

    defaultConfig {
        minSdk = 21
        targetSdk = 33

        applicationId = "dev.joseluisgs.meteocompose.androidApp"
        versionCode = 1
        versionName = "1.0.0"
    }
    sourceSets["main"].apply {
        manifest.srcFile("src/androidMain/AndroidManifest.xml")
        res.srcDirs("src/androidMain/resources")
        resources.srcDirs("src/commonMain/resources")
        res.srcDir("build/generated/libres/android/resources")
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    packagingOptions {
        resources.excludes.add("META-INF/**")
    }
}

// Configuración de Desktop
compose.desktop {
    application {
        mainClass = "MainDesktopKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "dev.joseluisgs.meteocompose.desktopApp"
            packageVersion = "1.0.0"
        }
    }
}

// Configuración de Libres
libres {
    // https://github.com/Skeptick/libres#setup
    /*
     generatedClassName = "MainRes" // "Res" by default
    generateNamedArguments = true // false by default
    baseLocaleLanguageCode = "ru" // "en" by default
    camelCaseNamesForAppleFramework = true // false by default
     */
}
tasks.getByPath("desktopProcessResources").dependsOn("libresGenerateResources")
tasks.getByPath("desktopSourcesJar").dependsOn("libresGenerateResources")
