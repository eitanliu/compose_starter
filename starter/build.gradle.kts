@file:OptIn(
    org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class,
)

import com.android.build.api.dsl.androidLibrary
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmCompilerOptions

apply(from = rootProject.file("gradle/publish_jitpack.gradle.kts"))
apply(from = rootProject.file("gradle/kmp_targets.gradle"))

plugins {
    alias(libs.plugins.android.multiplatform)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlin.multiplatform)
}

group = "com.eitanliu.compose"
version = "1.0.0"

kotlin {

    androidLibrary {
        namespace = "com.eitanliu.compose"
        compileSdk = libs.versions.android.compileSdk.get().toInt()
        minSdk = libs.versions.android.minSdk.get().toInt()
        compilations.all {
            compileTaskProvider {
                compilerOptions {
                    if (this is KotlinJvmCompilerOptions) {
                        jvmTarget.set(JvmTarget.JVM_11)
                    }
                }
            }
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "starter"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(libs.androidx.annotation)
            implementation(libs.androidx.annotation.experimental)
            implementation(libs.androidx.collection)
            implementation(libs.androidx.datastore)
            implementation(libs.androidx.datastore.preferences)
        }

        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }

        androidMain.dependencies {
            implementation(libs.androidx.activity.compose)
            implementation(libs.androidx.appcompat)
            implementation(libs.androidx.core.ktx)
        }

        androidUnitTest.dependencies {
            implementation(libs.androidx.test.ext.junit)
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

