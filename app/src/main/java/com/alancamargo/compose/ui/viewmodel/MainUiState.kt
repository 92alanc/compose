package com.alancamargo.compose.ui.viewmodel

import com.alancamargo.compose.ui.model.UiMyModel

data class MainUiState(val myModelList: List<UiMyModel>? = null) {

    fun onListReceived(myModelList: List<UiMyModel>) = copy(myModelList = myModelList)
}