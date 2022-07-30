package com.example.school_meal.data.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object SchoolAPIClient {
    lateinit var api : com.example.data.remote.SchoolInterface
    init {
        var retrofit = Retrofit.Builder()
            .baseUrl("https://open.neis.go.kr/hub/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        api = retrofit.create(com.example.data.remote.SchoolInterface::class.java)
    }
}