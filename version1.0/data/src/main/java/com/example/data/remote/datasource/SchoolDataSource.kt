package com.example.data.remote.datasource

import com.example.data.remote.response.MealResponse
import com.example.data.remote.response.SchoolInfoResponse

interface SchoolDataSource {
    suspend fun schoolInfo(schoolName: String): SchoolInfoResponse?
    suspend fun schoolMeal(day: String): MealResponse?
}