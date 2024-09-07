package com.alancamargo.compose.domain.repository

import com.alancamargo.compose.domain.model.MyModel

interface MyModelRepository {

    suspend fun getMyModelList(): List<MyModel>
}
