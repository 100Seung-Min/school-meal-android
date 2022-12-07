package com.example.school_meal.di

import com.example.data.remote.api.AuthAPI
import com.example.data.remote.api.SchoolAPI
import com.example.data.remote.datasource.AuthDataSourceImpl
import com.example.data.remote.datasource.SchoolDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteDataSourceModule {
    @Provides
    @Singleton
    fun provideAuthDataSource(service: AuthAPI) = AuthDataSourceImpl(service)

    @Provides
    @Singleton
    fun provideSchoolDataSource(service: SchoolAPI) = SchoolDataSourceImpl(service)
}