// Inidicamos los plugins que vamos a usar
// a nivel global del proyecto con un alias
plugins {
    alias(libs.plugins.multiplatform).apply(false)
    alias(libs.plugins.compose).apply(false)
    alias(libs.plugins.android.application).apply(false)
    alias(libs.plugins.kotlinx.serialization).apply(false)
    alias(libs.plugins.libres).apply(false)
}
