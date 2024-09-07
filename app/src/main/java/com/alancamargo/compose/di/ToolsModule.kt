package com.alancamargo.compose.di

import android.content.Context
import com.alancamargo.compose.tools.Logger
import com.alancamargo.compose.tools.LoggerImpl
import com.alancamargo.compose.tools.ToastHelper
import com.alancamargo.compose.tools.ToastHelperImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ToolsModule {

    @Provides
    @Singleton
    fun provideLogger(): Logger = LoggerImpl()

    @Provides
    @Singleton
    fun provideToastHelper(@ApplicationContext context: Context): ToastHelper {
        return ToastHelperImpl(context)
    }
}
