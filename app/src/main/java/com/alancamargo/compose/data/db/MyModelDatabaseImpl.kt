package com.alancamargo.compose.data.db

import com.alancamargo.compose.data.model.MyIconResponse
import com.alancamargo.compose.data.model.MyModelResponse
import javax.inject.Inject

class MyModelDatabaseImpl @Inject constructor() : MyModelDatabase {

    override suspend fun getMyModelList(): List<MyModelResponse> = listOf(
        MyModelResponse(icon = MyIconResponse.BIN, text = "This is a bin"),
        MyModelResponse(icon = MyIconResponse.CAR, text = "This is a car"),
        MyModelResponse(icon = MyIconResponse.LOCATION, text = "This is a location"),
        MyModelResponse(icon = MyIconResponse.PLANE, text = "This is a plane")
    )
}
