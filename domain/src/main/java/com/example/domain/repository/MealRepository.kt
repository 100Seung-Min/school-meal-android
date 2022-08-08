package com.example.domain.repository

import com.example.domain.entity.MealEntity

interface MealRepository {
    suspend fun getMeal(cityCode: String, schoolCode: String, mealType: String): MealEntity
}