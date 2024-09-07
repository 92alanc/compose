package com.alancamargo.compose.domain.usecase

import com.alancamargo.compose.domain.model.MyModel
import kotlinx.coroutines.flow.Flow

interface GetMyModelListUseCase {

    operator fun invoke(): Flow<List<MyModel>>
}
