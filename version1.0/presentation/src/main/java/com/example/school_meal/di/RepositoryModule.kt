package com.example.school_meal.di

import com.example.data.remote.datasource.AuthDataSourceImpl
import com.example.data.remote.datasource.SchoolDataSource
import com.example.data.remote.datasource.SchoolDataSourceImpl
import com.example.data.repository.AuthRepositoryImpl
import com.example.data.repository.SchoolRepositoryImpl
import com.example.domain.repository.AuthRepository
import com.example.domain.repository.SchoolRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ): AuthRepository

    @Binds
    abstract fun provideSchoolRepository(
        schoolRepositoryImpl: SchoolRepositoryImpl
    ): SchoolRepository
}