package com.alancamargo.compose.data.mapping

import com.alancamargo.compose.data.model.MyIconResponse
import com.alancamargo.compose.data.model.MyModelResponse
import com.alancamargo.compose.domain.model.MyIcon
import com.alancamargo.compose.domain.model.MyModel

fun MyModelResponse.toDomain() = MyModel(
    icon = icon.toDomain(),
    text = text
)

private fun MyIconResponse.toDomain() = when (this) {
    MyIconResponse.BIN -> MyIcon.BIN
    MyIconResponse.CAR -> MyIcon.CAR
    MyIconResponse.LOCATION -> MyIcon.LOCATION
    MyIconResponse.PLANE -> MyIcon.PLANE
}
