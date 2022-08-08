package com.example.domain.repository

import com.example.domain.entity.SchoolEntity

interface SchoolRepository {
    suspend fun getSchoolInfo(schoolName: String): SchoolEntity
}