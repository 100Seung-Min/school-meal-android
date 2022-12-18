package com.example.data.remote.datasource

import com.example.data.remote.api.SchoolAPI
import com.example.data.remote.response.MealResponse
import com.example.data.remote.response.SchoolInfoResponse
import javax.inject.Inject

class SchoolDataSourceImpl @Inject constructor(
    private val schoolAPI: SchoolAPI
): SchoolDataSource {
    override suspend fun schoolInfo(schoolName: String): List<SchoolInfoResponse>? {
        return schoolAPI.schoolInfo(schoolName)?.body()
    }

    override suspend fun schoolMeal(day: String): List<MealResponse>? {
        return schoolAPI.schoolMeal(day)?.body()
    }
}