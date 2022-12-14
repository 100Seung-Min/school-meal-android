package com.example.school_meal.di

import com.example.data.local.storage.LocalUserDataSource
import com.example.data.local.storage.LocalUserDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalDataSourceModule {
    @Binds
    abstract fun provideLocalUserDataSourceModule(
        localUserDataSourceImpl: LocalUserDataSourceImpl
    ): LocalUserDataSource
}