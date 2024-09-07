package com.alancamargo.compose.tools

interface Logger {

    fun logMessage(message: String)

    fun logError(message: String, exception: Throwable)
}
