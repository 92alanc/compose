package com.alancamargo.compose.di

import com.alancamargo.compose.data.local.MyModelLocalDataSource
import com.alancamargo.compose.data.local.MyModelLocalDataSourceImpl
import com.alancamargo.compose.data.repository.MyModelRepositoryImpl
import com.alancamargo.compose.domain.repository.MyModelRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class DataModule {

    @Binds
    @ViewModelScoped
    abstract fun bindMyModelLocalDataSource(
        impl: MyModelLocalDataSourceImpl
    ): MyModelLocalDataSource

    @Binds
    @ViewModelScoped
    abstract fun bindMyModelRepository(impl: MyModelRepositoryImpl): MyModelRepository
}
