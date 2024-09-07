package com.alancamargo.compose.data.local

import com.alancamargo.compose.data.db.MyModelDatabase
import com.alancamargo.compose.data.mapping.toDomain
import com.alancamargo.compose.domain.model.MyModel
import javax.inject.Inject

class MyModelLocalDataSourceImpl @Inject constructor(
    private val database: MyModelDatabase
) : MyModelLocalDataSource {

    override suspend fun getMyModelList(): List<MyModel> {
        val response = database.getMyModelList()
        return response.map { it.toDomain() }
    }
}
