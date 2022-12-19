package com.example.data.local.storage

interface LocalUserDataSource {
    fun setId(id: String?)
    fun getId(): String
}