package com.eitanliu.compose

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform