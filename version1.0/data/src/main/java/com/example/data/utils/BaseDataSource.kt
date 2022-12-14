package com.example.data.utils

import java.lang.Exception

abstract class BaseDataSource {
    suspend inline fun <T> safeApiCall(responseFunction: suspend () -> T): T? {
        return try {
            responseFunction.invoke()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}