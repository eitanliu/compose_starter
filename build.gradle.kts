plugins {
    // this is necessary to avoid the plugins to be loaded multiple times
    // in each subproject's classloader
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.android.multiplatform) apply false
    alias(libs.plugins.compose.multiplatform) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.kotlin.multiplatform) apply false
}

allprojects {

    configurations.all {
        resolutionStrategy {
            force(libs.androidx.core)
            force(libs.androidx.core.ktx)
            force(libs.kotlin.stdlib)
            force(libs.kotlin.stdlib.jdk7)
            force(libs.kotlin.stdlib.jdk8)
            force(libs.kotlinx.coroutines.core)
            force(libs.kotlinx.coroutines.core.jvm)
            force(libs.kotlinx.coroutines.android)
            force(libs.kotlinx.coroutines.swing)
        }
    }
}