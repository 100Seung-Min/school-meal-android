package com.example.data.remote.datasource

import com.example.data.remote.response.SchoolInfoData

interface SchoolDataSource {
    suspend fun getSchoolInfo(schoolName: String): SchoolInfoData
}