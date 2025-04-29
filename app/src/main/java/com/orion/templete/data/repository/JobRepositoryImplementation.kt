package com.orion.templete.data.repository

import android.content.Context
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.orion.templete.data.model.Job
import com.orion.templete.data.network.ApiService
import com.orion.templete.data.paging.JobsPagingSource
import com.orion.templete.domain.repository.JobRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class JobRepositoryImplementation @Inject constructor(
    private val apiService: ApiService,
    private val context: Context
    ) : JobRepository {
    override fun getJobs(): Flow<PagingData<Job>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false,
                prefetchDistance = 1
            ),
            pagingSourceFactory = {
                JobsPagingSource(apiService, context)
            }
        ).flow
    }
}