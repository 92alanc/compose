package com.alancamargo.compose.ui.viewmodel

sealed class MainUiAction {

    data class ShowToast(val text: String) : MainUiAction()
}
