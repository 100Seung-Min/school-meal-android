package com.example.school_meal.di

import com.example.data.remote.api.MealAPI
import com.example.data.remote.datasource.MealDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {
    @Provides
    @Singleton
    fun provideMealDataSource(service: MealAPI) = MealDataSourceImpl(service)
}