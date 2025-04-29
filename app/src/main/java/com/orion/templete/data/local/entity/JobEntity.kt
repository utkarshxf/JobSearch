package com.flashcall.me.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookmarks")
data class JobEntity(
    @PrimaryKey
    val id: Int,
    val company_name: String?,
    val salary_max: Int?,
    val salary_min: Int?,
    val title: String?,
    val city_location: Int?,
    val amount: String?,
    val button_text: String?,
    val content: String?,
    val job_type: Int?,
    val qualification: Int?,
    val whatsapp_no: String?,
    val primaryDetails_Experience: String?,
    val primaryDetails_Fees_Charged: String?,
    val primaryDetails_Job_Type: String?,
    val primaryDetails_Place: String?,
    val primaryDetails_Qualification: String?,
    val primaryDetails_Salary: String?
)
