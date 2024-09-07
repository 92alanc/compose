package com.alancamargo.compose.data.db

import com.alancamargo.compose.data.model.MyModelResponse

interface MyModelDatabase {

    suspend fun getMyModelList(): List<MyModelResponse>
}
