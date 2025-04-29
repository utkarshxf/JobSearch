package com.orion.templete.data.paging

import android.content.Context
import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.orion.templete.data.model.Job
import com.orion.templete.data.model.JobResponseDTO
import com.orion.templete.data.network.ApiService
import com.orion.templete.util.isNetworkAvailable
import org.json.JSONException
import org.json.JSONObject

class JobsPagingSource(
    private val apiService: ApiService,
    private val context: Context
) : PagingSource<Int, Job>() {

    override fun getRefreshKey(state: PagingState<Int, Job>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Job> {
        return try {
            val page = params.key ?: 1

            // Fetch from network if available
            if (isNetworkAvailable(context)) {
                val response = apiService.getJobs(page)
                if (response.isSuccessful) {
                    val jobsResponse = response.body() ?: JobResponseDTO(emptyList())
                    Log.d("JobsPagingSource", "Response: $page")
                    val jobs = jobsResponse.results

                    LoadResult.Page(
                        data = jobs,
                        prevKey = if (page == 1) null else page - 1,
                        nextKey = if (jobs.isEmpty()) null else page + 1
                    )
                } else {
                    val responseErr = response.errorBody()?.string()
                    val message = StringBuilder()
                    responseErr.let {
                        try {
                            message.append(JSONObject(it).getString("error"))
                        } catch (e: JSONException) {
                            Log.e("JobsPagingSource", "JSONException: ${e.message}")
                        }
                    }
                    throw Exception("error code: ${response.code()}: "+responseErr)
                }
            } else {
                LoadResult.Error(Exception("No internet connection and no cached data available"))
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}
