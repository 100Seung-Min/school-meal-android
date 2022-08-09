package com.example.data.remote.api

import com.example.data.remote.response.SchoolResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SchoolAPI {
    @GET("schoolInfo")
    suspend fun getSchoolInfo(
        @Query("KEY") key: String = "dfed562db5ef4e88b1e71079c0039615",
        @Query("Type") type: String = "json",
        @Query("pIndex") index: String = "1",
        @Query("pSize") size: String = "100",
        @Query("SCHUL_NM") schoolName: String
    ) : Response<SchoolResponse>
}