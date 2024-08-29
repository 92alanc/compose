package com.alancamargo.compose.ui.viewmodel

data class MainUiState(
    val text: String = "Untouched screen",
    val fabClickCount: Int = 0
) {

    fun onTextChanged(text: String) = copy(text = text)

    fun onFabClickCountChanged(fabClickCount: Int) = copy(fabClickCount = fabClickCount)
}