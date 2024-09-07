package com.alancamargo.compose.data.local

import com.alancamargo.compose.domain.model.MyModel

interface MyModelLocalDataSource {

    suspend fun getMyModelList(): List<MyModel>
}
