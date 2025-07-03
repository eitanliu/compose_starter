@file:OptIn(ExperimentalForeignApi::class)

package com.eitanliu.compose
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.refTo
import kotlinx.cinterop.toKString
import platform.posix.*
import platform.darwin.*

class NativePlatform : Platform {
    override val name: String = "Native"
}

actual fun getPlatform(): Platform = NativePlatform()

fun a() {
    val buffer = ByteArray(1024)
    val ptr = buffer.refTo(0)
    val cwd = getcwd(ptr, buffer.size.toULong())
    if (cwd != null) {
        val currentDir = cwd.toKString()
        println("Current directory: $currentDir")
    } else {
        perror("getcwd() error")
    }
}

fun getExecutableDirectory(): String {
    val pathBuffer = ByteArray(4096)
    val count = readlink("/proc/self/exe", pathBuffer.refTo(0), pathBuffer.size.toULong())
    if (count < 0) {
        throw RuntimeException("Failed to read /proc/self/exe")
    }

    val executablePath = pathBuffer.toKString().substring(0, count.toInt())
    return executablePath.substringBeforeLast('/')
}