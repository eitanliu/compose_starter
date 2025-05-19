package com.eitanliu.compose.app

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform