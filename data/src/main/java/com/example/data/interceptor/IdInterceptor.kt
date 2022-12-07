package com.example.data.interceptor

import android.content.Context
import com.example.data.local.storage.LocalUserDataSource
import okhttp3.*
import javax.inject.Inject

class IdInterceptor @Inject constructor(
    private val localUserDataSource: LocalUserDataSource
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        return chain.proceed(
            request.newBuilder().addHeader(
                "id",
                localUserDataSource.getId()
            ).build()
        )
    }
}