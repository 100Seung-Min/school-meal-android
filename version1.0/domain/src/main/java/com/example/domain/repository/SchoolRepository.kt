package com.example.domain.repository

import com.example.domain.entity.MealEntity
import com.example.domain.entity.SchoolInfoEntity
import com.example.domain.entity.TimeEntity

interface SchoolRepository {
    suspend fun schoolInfo(schoolName: String): List<SchoolInfoEntity>?
    suspend fun schoolMeal(day: String): List<MealEntity>?
    suspend fun schoolTime(): List<TimeEntity>?
}