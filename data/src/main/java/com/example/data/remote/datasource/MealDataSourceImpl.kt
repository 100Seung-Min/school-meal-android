package com.example.data.remote.datasource

import com.example.data.remote.api.MealAPI
import com.example.data.remote.response.MealResponse
import javax.inject.Inject

class MealDataSourceImpl @Inject constructor(
    private val mealAPI: MealAPI
): MealDataSource {
    override suspend fun getMeal(
        cityCode: String,
        schoolCode: String,
        mealMonth: String,
    ): MealResponse {
        return mealAPI.getMeal(cityCode = cityCode, schoolCode = schoolCode, mealMonth = mealMonth)
    }

}