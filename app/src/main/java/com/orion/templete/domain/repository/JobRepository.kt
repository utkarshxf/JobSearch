package com.orion.templete.domain.repository

import androidx.paging.PagingData
import com.orion.templete.data.model.Job
import kotlinx.coroutines.flow.Flow

interface JobRepository  {
    fun getJobs(): Flow<PagingData<Job>>
}