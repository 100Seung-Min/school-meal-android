package com.example.domain.repository

import com.example.domain.entity.SchoolInfoEntity

interface SchoolRepository {
    suspend fun schoolInfo(schoolName: String): SchoolInfoEntity?
}