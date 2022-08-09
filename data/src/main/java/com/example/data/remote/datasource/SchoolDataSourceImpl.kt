package com.example.data.remote.datasource

import com.example.data.remote.api.SchoolAPI
import com.example.data.remote.response.SchoolResponse
import com.example.data.utils.BaseDataSource
import javax.inject.Inject

class SchoolDataSourceImpl @Inject constructor(
    private val schoolAPI: SchoolAPI,
) : SchoolDataSource, BaseDataSource() {
    override suspend fun getSchoolInfo(schoolName: String): SchoolResponse? {
        return safeApiCall { schoolAPI.getSchoolInfo(schoolName = schoolName) }?.body()
    }
}