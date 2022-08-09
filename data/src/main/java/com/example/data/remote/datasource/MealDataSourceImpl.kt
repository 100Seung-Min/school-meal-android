package com.example.data.remote.datasource

import com.example.data.remote.api.MealAPI
import com.example.data.remote.response.MealResponse
import com.example.data.utils.BaseDataSource
import javax.inject.Inject

class MealDataSourceImpl @Inject constructor(
    private val mealAPI: MealAPI
): MealDataSource, BaseDataSource() {
    override suspend fun getMeal(
        cityCode: String,
        schoolCode: String,
        mealType: String,
    ): MealResponse? {
        return safeApiCall { mealAPI.getMeal(cityCode = cityCode, schoolCode = schoolCode, mealType = mealType) }?.body()
    }

}