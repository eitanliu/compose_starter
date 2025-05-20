package com.eitanliu.compose

class NativePlatform : Platform {
    override val name: String = "Native"
}

actual fun getPlatform(): Platform = NativePlatform()