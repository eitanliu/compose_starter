@file:Suppress("UnstableApiUsage")

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        maven {
            url = uri("https://maven.pkg.jetbrains.space/public/p/compose/dev/")
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("org.jetbrains")
            }
        }
        // AndroidX Dev KMP
        // maven {
        //     url = uri("https://androidx.dev/kmp/builds/13810188/artifacts/snapshots/repository")
        //     mavenContent {
        //         includeGroupAndSubgroups("androidx")
        //     }
        // }
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.10.0"
}

rootProject.name = "compose_starter"
include(":app")
include(":starter")