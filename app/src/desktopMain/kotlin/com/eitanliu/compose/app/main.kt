package com.eitanliu.compose.app

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "compose_starter",
    ) {
        App()
    }
}