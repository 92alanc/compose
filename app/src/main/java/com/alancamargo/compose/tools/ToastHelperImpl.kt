package com.alancamargo.compose.tools

import android.content.Context
import android.widget.Toast
import javax.inject.Inject

class ToastHelperImpl @Inject constructor(private val context: Context) : ToastHelper {

    override fun showToast(text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }
}
