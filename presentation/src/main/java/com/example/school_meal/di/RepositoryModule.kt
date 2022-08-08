package com.example.school_meal.di

import com.example.data.remote.datasource.MealDataSourceImpl
import com.example.data.remote.datasource.SchoolDataSourceImpl
import com.example.data.remote.datasource.TimeDataSourceImpl
import com.example.data.repository.MealRepositoryImpl
import com.example.data.repository.SchoolRepositoryImpl
import com.example.data.repository.TimeRepositoryImpl
import com.example.domain.repository.MealRepository
import com.example.domain.repository.SchoolRepository
import com.example.domain.repository.TimeRepository
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
    fun provideMealRepository(dataSource: MealDataSourceImpl): MealRepository = MealRepositoryImpl(dataSource)

    @Provides
    @Singleton
    fun provideSchoolRepository(dataSource: SchoolDataSourceImpl): SchoolRepository = SchoolRepositoryImpl(dataSource)

    @Provides
    @Singleton
    fun provideTimeRepository(dataSource: TimeDataSourceImpl): TimeRepository = TimeRepositoryImpl(dataSource)
}