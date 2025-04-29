package com.orion.templete.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PrimaryDetails(
    val Experience: String?=null,
    val Fees_Charged: String?=null,
    val Job_Type: String?=null,
    val Place: String?=null,
    val Qualification: String?=null,
    val Salary: String?=null
):Parcelable