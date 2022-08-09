package com.example.data.remote.datasource

import com.example.data.remote.response.SchoolResponse

interface SchoolDataSource {
    suspend fun getSchoolInfo(schoolName: String): SchoolResponse?
}