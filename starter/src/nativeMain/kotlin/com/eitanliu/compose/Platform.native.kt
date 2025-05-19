package com.eitanliu.compose

class AndroidPlatform : Platform {
    override val name: String = "Native"
}

actual fun getPlatform(): Platform = AndroidPlatform()