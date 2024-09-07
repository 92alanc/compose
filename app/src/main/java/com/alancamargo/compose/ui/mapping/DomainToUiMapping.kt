package com.alancamargo.compose.ui.mapping

import com.alancamargo.compose.domain.model.MyIcon
import com.alancamargo.compose.domain.model.MyModel
import com.alancamargo.compose.ui.model.UiMyIcon
import com.alancamargo.compose.ui.model.UiMyModel

fun MyModel.toUi() = UiMyModel(
    icon = icon.toUi(),
    text = text
)

private fun MyIcon.toUi() = when (this) {
    MyIcon.BIN -> UiMyIcon.BIN
    MyIcon.CAR -> UiMyIcon.CAR
    MyIcon.LOCATION -> UiMyIcon.LOCATION
    MyIcon.PLANE -> UiMyIcon.PLANE
}
