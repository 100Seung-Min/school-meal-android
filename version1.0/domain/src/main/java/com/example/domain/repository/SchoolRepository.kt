package com.example.domain.repository

import com.example.domain.entity.MealEntity
import com.example.domain.entity.SchoolInfoEntity

interface SchoolRepository {
    suspend fun schoolInfo(schoolName: String): List<SchoolInfoEntity>?
    suspend fun schoolMeal(day: String): List<MealEntity>?
}