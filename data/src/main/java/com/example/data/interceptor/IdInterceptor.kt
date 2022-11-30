package com.example.data.interceptor

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.*
import javax.inject.Inject

class IdInterceptor @Inject constructor(
    @ApplicationContext private val context: Context
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val path = request.url().encodedPath()
        return chain.proceed(
            request.newBuilder().addHeader(
                "Id",
                context.getSharedPreferences("Id", 0).getString("id", "") ?: ""
            ).build()
        )
    }
}