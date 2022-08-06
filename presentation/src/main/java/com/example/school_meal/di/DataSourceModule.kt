package com.example.school_meal.di

import com.example.data.remote.api.MealAPI
import com.example.data.remote.api.SchoolAPI
import com.example.data.remote.api.TimeAPI
import com.example.data.remote.datasource.MealDataSourceImpl
import com.example.data.remote.datasource.SchoolDataSourceImpl
import com.example.data.remote.datasource.TimeDataSourceImpl
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

    @Provides
    @Singleton
    fun provideSchoolDataSource(service: SchoolAPI) = SchoolDataSourceImpl(service)

    @Provides
    @Singleton
    fun provideTimeDataSource(service: TimeAPI) = TimeDataSourceImpl(service)
}