package com.example.school_meal.di

import com.example.data.remote.datasource.MealDataSourceImpl
import com.example.data.repository.MealRepositoryImpl
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
    fun provideMealRepository(dataSource: MealDataSourceImpl) = MealRepositoryImpl(dataSource)
}