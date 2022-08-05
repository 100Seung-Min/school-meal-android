package com.example.data.remote.api

import com.example.data.remote.response.SchoolTimeDate
import retrofit2.http.GET
import retrofit2.http.Query
import java.text.SimpleDateFormat
import java.util.*

interface TimeAPI {

    @GET("hisTimetable")
    fun getHisTime(
        @Query("KEY") key: String = "dfed562db5ef4e88b1e71079c0039615",
        @Query("Type") type: String = "json",
        @Query("pIndex") index: String = "1",
        @Query("pSize") size: String = "100",
        @Query("ATPT_OFCDC_SC_CODE") cityCode: String,
        @Query("SD_SCHUL_CODE") schoolCode: String,
        @Query("GRADE") grade: String,
        @Query("SEM") term: String = if (SimpleDateFormat("MM").format(Date())
                .toInt() < 7
        ) "1" else "2",
        @Query("CLASS_NM") className: String,
        @Query("TI_FROM_YMD") startDay: String,
        @Query("TI_TO_YMD") endDay: String,
    ): SchoolTimeDate

    @GET("misTimetable")
    fun getMisTime(
        @Query("KEY") key: String = "dfed562db5ef4e88b1e71079c0039615",
        @Query("Type") type: String = "json",
        @Query("pIndex") index: String = "1",
        @Query("pSize") size: String = "100",
        @Query("SEM") term: String = if (SimpleDateFormat("MM").format(Date())
                .toInt() < 7
        ) "1" else "2",
        @Query("TI_FROM_YMD") startDay: String,
        @Query("TI_TO_YMD") endDay: String,
        @Query("ATPT_OFCDC_SC_CODE") cityCode: String,
        @Query("SD_SCHUL_CODE") schoolCode: String,
        @Query("GRADE") grade: String,
        @Query("CLASS_NM") className: String,
    ): SchoolTimeDate

    @GET("elsTimetable")
    fun getElsTime(
        @Query("KEY") key: String = "dfed562db5ef4e88b1e71079c0039615",
        @Query("Type") type: String = "json",
        @Query("pIndex") index: String = "1",
        @Query("pSize") size: String = "100",
        @Query("SEM") term: String = if (SimpleDateFormat("MM").format(Date())
                .toInt() < 7
        ) "1" else "2",
        @Query("TI_FROM_YMD") startDay: String,
        @Query("TI_TO_YMD") endDay: String,
        @Query("ATPT_OFCDC_SC_CODE") cityCode: String,
        @Query("SD_SCHUL_CODE") schoolCode: String,
        @Query("GRADE") grade: String,
        @Query("CLASS_NM") className: String,
    ): SchoolTimeDate
}