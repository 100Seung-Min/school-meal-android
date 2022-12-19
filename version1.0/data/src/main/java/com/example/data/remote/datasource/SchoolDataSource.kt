package com.example.data.remote.datasource

import com.example.data.remote.response.MealResponse
import com.example.data.remote.response.SchoolInfoResponse
import com.example.data.remote.response.TimeResponse

interface SchoolDataSource {
    suspend fun schoolInfo(schoolName: String): List<SchoolInfoResponse>?
    suspend fun schoolMeal(day: String): List<MealResponse>?
    suspend fun schoolTime(): List<TimeResponse>?
}