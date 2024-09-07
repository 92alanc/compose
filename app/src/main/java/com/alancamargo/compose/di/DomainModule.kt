package com.alancamargo.compose.di

import com.alancamargo.compose.domain.usecase.GetMyModelListUseCase
import com.alancamargo.compose.domain.usecase.GetMyModelListUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class DomainModule {

    @Binds
    @ViewModelScoped
    abstract fun bindGetMyModelListUseCase(impl: GetMyModelListUseCaseImpl): GetMyModelListUseCase
}
