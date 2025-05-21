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
            force(libs.androidx.lifecycle.common)
            force(libs.androidx.lifecycle.viewmodel.compose)
            force(libs.androidx.lifecycle.viewmodel.savedstate)
            force(libs.androidx.lifecycle.runtime)
            force(libs.androidx.lifecycle.runtime.compose)
            force(libs.compose.animation)
            force(libs.compose.animationGraphics)
            force(libs.compose.foundation)
            force(libs.compose.runtime)
            force(libs.compose.runtimeSaveable)
            force(libs.compose.ui)
            force(libs.compose.uiTest)
            force(libs.compose.uiTooling)
            force(libs.compose.uiUtil)
            force(libs.compose.preview)
            force(libs.compose.components.resources)
            force(libs.compose.components.uiToolingPreview)
            force(libs.compose.desktop)
            force(libs.compose.desktop.linuxX64)
            force(libs.compose.desktop.linuxArm64)
            force(libs.compose.desktop.macosX64)
            force(libs.compose.desktop.macosArm64)
            force(libs.compose.desktop.windowsArm64)
            force(libs.compose.desktop.windowsX64)
            force(libs.compose.desktop.uiTestJUnit4)
            force(libs.compose.desktop.components.splitPane)
            force(libs.compose.desktop.components.animatedImage)
            force(libs.compose.material)
            force(libs.compose.material3)
            force(libs.compose.material3AdaptiveNavigationSuite)
            force(libs.compose.lifecycle.common)
            force(libs.compose.lifecycle.viewmodel)
            force(libs.compose.lifecycle.viewmodel.compose)
            force(libs.compose.lifecycle.viewmodel.savedstate)
            force(libs.compose.lifecycle.runtime)
            force(libs.compose.lifecycle.runtime.compose)

            force(libs.kotlin.stdlib)
            force(libs.kotlin.stdlib.jdk7)
            force(libs.kotlin.stdlib.jdk8)
            force(libs.kotlinx.coroutines.core)
            force(libs.kotlinx.coroutines.core.jvm)
            force(libs.kotlinx.coroutines.android)
            force(libs.kotlinx.coroutines.swing)
            force(libs.okio)
        }
    }
}