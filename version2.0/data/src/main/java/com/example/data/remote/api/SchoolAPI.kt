package com.example.data.remote.api

import com.example.data.remote.response.MealResponse
import com.example.data.remote.response.SchoolInfoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SchoolAPI {
    @GET("school")
    suspend fun schoolInfo(
        @Query("schoolName") schoolName: String
    ): Response<SchoolInfoResponse>?

    @GET("school/meal")
    suspend fun schoolMeal(
        @Query("day") day: String
    ): Response<MealResponse>?
}