package com.orion.templete.data.network

import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param
import com.orion.templete.data.model.JobResponseDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("common/jobs")
    suspend fun getJobs(
        @Query("page") page: Int
    ): retrofit2.Response<JobResponseDTO>

    companion object {
        var baseurl = "https://testapi.getlokalapp.com/"
    }
}