package com.example.data.remote.datasource

import com.example.data.remote.api.SchoolAPI
import com.example.data.remote.response.SchoolInfoData
import javax.inject.Inject

class SchoolDataSourceImpl @Inject constructor(
    private val schoolAPI: SchoolAPI
): SchoolDataSource {
    override suspend fun getSchoolInfo(schoolName: String): SchoolInfoData {
        return schoolAPI.getSchoolInfo(schoolName = schoolName)
    }
}