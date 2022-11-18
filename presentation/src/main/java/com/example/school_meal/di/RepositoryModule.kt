package com.example.school_meal.di

import com.example.data.remote.datasource.AuthDataSourceImpl
import com.example.data.remote.datasource.SchoolDataSource
import com.example.data.remote.datasource.SchoolDataSourceImpl
import com.example.data.repository.AuthRepositoryImpl
import com.example.data.repository.SchoolRepositoryImpl
import com.example.domain.repository.AuthRepository
import com.example.domain.repository.SchoolRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideAuthRepository(dataSource: AuthDataSourceImpl): AuthRepository = AuthRepositoryImpl(dataSource)

    @Provides
    @Singleton
    fun provideSchoolRepository(dataSource: SchoolDataSourceImpl): SchoolRepository = SchoolRepositoryImpl(dataSource)
}