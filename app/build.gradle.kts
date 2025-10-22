@file:OptIn(
    org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class,
)

import org.gradle.internal.os.OperatingSystem
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.compose.hot.reload)
    alias(libs.plugins.kotlin.multiplatform)
}


private val os get() = OperatingSystem.current()
private val arch get() = System.getProperty("os.arch")
private val isArm64 get() = arch == "aarch64"

val androidEnable = true
val iosEnable get() = os.isMacOsX
val desktopEnable = true

kotlin {
    if (androidEnable) androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    if (iosEnable) listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }

    if (desktopEnable) jvm("desktop")

    sourceSets {

        commonMain.dependencies {
            implementation(compose.animation)
            implementation(compose.animationGraphics)
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.ui)
            implementation(compose.material3)
            implementation(compose.material3AdaptiveNavigationSuite)
            implementation(compose.materialIconsExtended)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)

            implementation(libs.compose.core.bundle)
            implementation(libs.compose.core.uri)
            implementation(libs.compose.lifecycle.viewmodel)
            implementation(libs.compose.lifecycle.runtime.compose)
            // implementation(libs.compose.navigation)
            implementation(libs.compose.navigation3)

            implementation(project.dependencies.platform(libs.androidx.compose.bom))
            // implementation(libs.androidx.compose.animation)
            // implementation(libs.androidx.compose.animation.graphics)
            // implementation(libs.androidx.compose.runtime)
            // implementation(libs.androidx.compose.foundation)
            // implementation(libs.androidx.compose.ui)
            // implementation(libs.androidx.compose.material3)
            // implementation(libs.androidx.compose.material3.adaptive.navigation.suite)
            // implementation(libs.androidx.compose.material.icons.extended)
            // implementation(libs.androidx.navigation.ui)
            // implementation(libs.androidx.navigation3.runtime)
            // implementation(libs.androidx.navigation3.ui)

            implementation(project.dependencies.platform(libs.kotlinx.coroutines.bom))
            implementation(libs.kotlinx.coroutines.core)
        }

        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }

        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
        }

        // val desktopMain by getting
        if (desktopEnable) getByName("desktopMain").dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.kotlinx.coroutines.swing)
        }

        all {
            languageSettings.apply {
                optIn("androidx.compose.material3.ExperimentalMaterial3Api")
                optIn("kotlin.ExperimentalStdlibApi")
                optIn("kotlin.experimental.ExperimentalNativeApi")
                optIn("kotlin.native.runtime.NativeRuntimeApi")
                optIn("kotlin.time.ExperimentalTime")
                // 2.1.2
                optIn("kotlin.concurrent.atomics.ExperimentalAtomicApi")
                optIn("kotlin.uuid.ExperimentalUuidApi")

                optIn("kotlinx.cinterop.ExperimentalForeignApi")
                optIn("kotlinx.coroutines.DelicateCoroutinesApi")
                optIn("kotlinx.coroutines.ExperimentalCoroutinesApi")
            }
        }
    }

    compilerOptions {
        // 2.1.0 https://kotlinlang.org/docs/whatsnew21.html#guard-conditions-in-when-with-a-subject
        freeCompilerArgs.add("-Xwhen-guards")
    }
}

android {
    namespace = "com.eitanliu.compose.app"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.eitanliu.compose.app"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    signingConfigs {
        getByName("debug") {
            storeFile = rootProject.file("debug.jks")
            storePassword = "debug1"
            keyAlias = "debug"
            keyPassword = "debug1"
        }
    }

    buildTypes {
        debug {
            signingConfig = signingConfigs.getByName("debug")
        }

        release {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    debugImplementation(compose.uiTooling)
}

if (desktopEnable) compose.desktop {
    application {
        mainClass = "com.eitanliu.compose.app.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "com.eitanliu.compose.app"
            packageVersion = "1.0.0"
        }
    }
}
