package com.alancamargo.compose.domain.usecase

import com.alancamargo.compose.domain.model.MyModel
import com.alancamargo.compose.domain.repository.MyModelRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMyModelListUseCaseImpl @Inject constructor(
    private val repository: MyModelRepository
) : GetMyModelListUseCase {

    override fun invoke(): Flow<List<MyModel>> = flow {
        val list = repository.getMyModelList()
        emit(list)
    }
}
