package com.example.data.remote

import com.example.data.model.SchoolInfoData
import com.example.data.model.SchoolMealData
import com.example.data.model.SchoolTimeDate
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import java.text.SimpleDateFormat
import java.util.*

interface SchoolAPI {
    @GET("mealServiceDietInfo")
    fun getMeal(
        @Query("KEY") key: String = "dfed562db5ef4e88b1e71079c0039615",
        @Query("Type") type: String = "json",
        @Query("pIndex") index: String = "1",
        @Query("pSize") size: String = "100",
        @Query("ATPT_OFCDC_SC_CODE") cityCode: String,
        @Query("SD_SCHUL_CODE") schoolCode: String,
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

    @GET("hisTimetable")
    fun getHisTime(
        @Query("KEY") key: String = "dfed562db5ef4e88b1e71079c0039615",
        @Query("Type") type: String = "json",
        @Query("pIndex") index: String = "1",
        @Query("pSize") size: String = "100",
        @Query("ATPT_OFCDC_SC_CODE") cityCode: String,
        @Query("SD_SCHUL_CODE") schoolCode: String,
        @Query("GRADE") grade: String,
        @Query("SEM") term: String = if(SimpleDateFormat("MM").format(Date()).toInt() < 7) "1" else "2",
        @Query("CLASS_NM") className: String,
        @Query("TI_FROM_YMD") startDay: String,
        @Query("TI_TO_YMD") endDay: String
    ): Call<SchoolTimeDate>

    @GET("misTimetable")
    fun getMisTime(
        @Query("KEY") key: String = "dfed562db5ef4e88b1e71079c0039615",
        @Query("Type") type: String = "json",
        @Query("pIndex") index: String = "1",
        @Query("pSize") size: String = "100",
        @Query("SEM") term: String = if(SimpleDateFormat("MM").format(Date()).toInt() < 7) "1" else "2",
        @Query("TI_FROM_YMD") startDay: String,
        @Query("TI_TO_YMD") endDay: String,
        @Query("ATPT_OFCDC_SC_CODE") cityCode: String,
        @Query("SD_SCHUL_CODE") schoolCode: String,
        @Query("GRADE") grade: String,
        @Query("CLASS_NM") className: String
    ): Call<SchoolTimeDate>

    @GET("elsTimetable")
    fun getElsTime(
        @Query("KEY") key: String = "dfed562db5ef4e88b1e71079c0039615",
        @Query("Type") type: String = "json",
        @Query("pIndex") index: String = "1",
        @Query("pSize") size: String = "100",
        @Query("SEM") term: String = if(SimpleDateFormat("MM").format(Date()).toInt() < 7) "1" else "2",
        @Query("TI_FROM_YMD") startDay: String,
        @Query("TI_TO_YMD") endDay: String,
        @Query("ATPT_OFCDC_SC_CODE") cityCode: String,
        @Query("SD_SCHUL_CODE") schoolCode: String,
        @Query("GRADE") grade: String,
        @Query("CLASS_NM") className: String
    ): Call<SchoolTimeDate>
}