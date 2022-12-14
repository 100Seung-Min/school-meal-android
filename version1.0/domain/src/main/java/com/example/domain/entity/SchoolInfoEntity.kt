package com.example.domain.entity

data class SchoolInfoEntity(
    val row: List<SchoolInfo>
){
    data class SchoolInfo(
        val schoolName: String,
        val cityCode: String,
        val schoolCode: String,
        val schoolClass: String
    )
}
