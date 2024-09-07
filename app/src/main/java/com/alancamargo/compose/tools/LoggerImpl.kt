package com.alancamargo.compose.tools

import android.util.Log
import javax.inject.Inject

private const val TAG = "TEST_ALAN"

class LoggerImpl @Inject constructor() : Logger {

    override fun logMessage(message: String) {
        Log.d(TAG, message)
    }

    override fun logError(message: String, exception: Throwable) {
        Log.e(TAG, message, exception)
    }
}