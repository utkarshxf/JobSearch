package com.orion.templete.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable

@Parcelize
data class Job(
    val company_name: String? = null,
    val id: Int,
    val salary_max: Int? = null,
    val salary_min: Int? = null,
    val title: String? = null,
    val city_location: Int? = null,
    val amount: String? = null,
    val button_text: String? = null,
    val content: String? = null,
    val job_type: Int? = null,
    val primary_details: PrimaryDetails? = null,
    val qualification: Int? = null,
    val whatsapp_no: String? = null
): Parcelable