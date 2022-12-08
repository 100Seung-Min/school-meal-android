package com.example.school_meal.di

import com.example.data.remote.api.AuthAPI
import com.example.data.remote.api.SchoolAPI
import com.example.data.remote.datasource.AuthDataSource
import com.example.data.remote.datasource.AuthDataSourceImpl
import com.example.data.remote.datasource.SchoolDataSource
import com.example.data.remote.datasource.SchoolDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteDataSourceModule {
    @Binds
    abstract fun provideAuthDataSource(
        authDataSourceImpl: AuthDataSourceImpl
    ): AuthDataSource

    @Binds
    abstract fun provideSchoolDataSource(
        schoolDataSourceImpl: SchoolDataSourceImpl
    ): SchoolDataSource
}