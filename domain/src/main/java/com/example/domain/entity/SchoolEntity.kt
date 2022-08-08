package com.example.domain.entity

data class SchoolEntity(
    val schoolInfo: List<SchoolInfo>
) {
    data class SchoolInfo(
        val row: List<InfoRow>
    ) {
        data class InfoRow(
            val schoolName: String,
            val cityCode: String,
            val schoolCode: String,
            val schoolType: String
        )
    }
}
