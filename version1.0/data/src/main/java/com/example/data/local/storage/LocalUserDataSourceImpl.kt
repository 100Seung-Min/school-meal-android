package com.example.data.local.storage

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class LocalUserDataSourceImpl @Inject constructor(
    @ApplicationContext private val context: Context
): LocalUserDataSource {
    companion object {
        const val USER = "USER"
        const val ID = "ID"
    }


    override fun setId(id: String) {
        getSharedPreferences().edit().putString(ID, id).apply()
    }

    override fun getId(): String =
        getSharedPreferences().getString(ID, "") ?: ""

    private fun getSharedPreferences() =
        context.getSharedPreferences(USER, Context.MODE_PRIVATE)
}