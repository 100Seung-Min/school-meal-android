package com.example.data.remote.datasource

import com.example.data.remote.response.SchoolMealData

interface MealDataSource {
    suspend fun getMeal(cityCode: String, schoolCode: String, mealMonth: String): SchoolMealData
}