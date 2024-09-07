package com.alancamargo.compose.data.repository

import com.alancamargo.compose.data.local.MyModelLocalDataSource
import com.alancamargo.compose.domain.model.MyModel
import com.alancamargo.compose.domain.repository.MyModelRepository
import javax.inject.Inject

class MyModelRepositoryImpl @Inject constructor(
    private val localDataSource: MyModelLocalDataSource
) : MyModelRepository {

    override suspend fun getMyModelList(): List<MyModel> {
        return localDataSource.getMyModelList()
    }
}
