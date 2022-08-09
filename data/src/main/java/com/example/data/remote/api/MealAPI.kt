package com.example.data.remote.api

import com.example.data.remote.response.MealResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import java.text.SimpleDateFormat
import java.util.*

interface MealAPI {
    @GET("mealServiceDietInfo")
    suspend fun getMeal(
        @Query("KEY") key: String = "dfed562db5ef4e88b1e71079c0039615",
        @Query("Type") type: String = "json",
        @Query("pIndex") index: String = "1",
        @Query("pSize") size: String = "100",
        @Query("MLSV_YMD") mealMonth: String = SimpleDateFormat("yyyyMM").format(Date()),
        @Query("ATPT_OFCDC_SC_CODE") cityCode: String,
        @Query("SD_SCHUL_CODE") schoolCode: String,
        @Query("MMEAL_SC_CODE") mealType: String
    ): Response<MealResponse>
}