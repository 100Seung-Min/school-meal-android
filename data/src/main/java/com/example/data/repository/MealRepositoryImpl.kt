package com.example.data.repository

import com.example.data.remote.datasource.MealDataSourceImpl
import com.example.data.remote.response.toEntity
import com.example.domain.entity.MealEntity
import com.example.domain.repository.MealRepository
import javax.inject.Inject

class MealRepositoryImpl @Inject constructor(
    private val mealDataSource: MealDataSourceImpl
): MealRepository {
    override suspend fun getMeal(
        cityCode: String,
        schoolCode: String,
        mealType: String,
    ): MealEntity? {
        return mealDataSource.getMeal(cityCode = cityCode, schoolCode = schoolCode, mealType = mealType)?.toEntity()
    }
}