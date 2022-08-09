package com.example.data.remote.datasource

import com.example.data.remote.response.MealResponse

interface MealDataSource {
    suspend fun getMeal(cityCode: String, schoolCode: String, mealType: String): MealResponse?
}