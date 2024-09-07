package com.alancamargo.compose.di

import com.alancamargo.compose.data.db.MyModelDatabase
import com.alancamargo.compose.data.db.MyModelDatabaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideMyModelDatabase(): MyModelDatabase = MyModelDatabaseImpl()
}
