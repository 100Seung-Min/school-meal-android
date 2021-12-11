package com.example.school_meal.retrofit

import com.example.school_meal.DTO.SchoolInfoData
import com.example.school_meal.DTO.SchoolMealData
import com.example.school_meal.DTO.schoolInfo
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import java.text.SimpleDateFormat
import java.util.*

interface SchoolInterface {
    @GET("mealServiceDietInfo")
    fun getMeal(
        @Query("KEY") key: String = "dfed562db5ef4e88b1e71079c0039615",
        @Query("Type") type: String = "json",
        @Query("pIndex") index: String = "1",
        @Query("pSize") size: String = "100",
        @Query("ATPT_OFCDC_SC_CODE") sityCode: String = "F10",
        @Query("SD_SCHUL_CODE") schoolCode: String = "7380292",
        @Query("MLSV_YMD") mealMonth: String = SimpleDateFormat("yyyyMM").format(Date())
    ): Call<SchoolMealData>

    @GET("schoolInfo")
    fun getSchoolInfo(
        @Query("KEY") key: String = "dfed562db5ef4e88b1e71079c0039615",
        @Query("Type") type: String = "json",
        @Query("pIndex") index: String = "1",
        @Query("pSize") size: String = "100",
        @Query("SCHUL_NM") schoolName: String
    ) : Call<SchoolInfoData>
}