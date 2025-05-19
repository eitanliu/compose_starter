import com.android.build.api.dsl.androidLibrary

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

        // compilations {
        //     all {
        //         kotlinOptions {
        //             jvmTarget = JvmTarget.JVM_1_8.target
        //         }
        //     }
        // }
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
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

