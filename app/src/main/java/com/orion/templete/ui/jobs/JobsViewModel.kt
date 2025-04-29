package com.orion.templete.ui.jobs
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.orion.templete.data.model.Job
import com.orion.templete.domain.repository.JobRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JobsViewModel @Inject constructor(
    private val jobRepository: JobRepository
) : ViewModel() {

    // StateFlow for jobs PagingData
    private val _jobsFlow = MutableStateFlow<PagingData<Job>>(PagingData.empty())
    val jobsFlow: StateFlow<PagingData<Job>> = _jobsFlow
    init {
        loadJobs()
    }
    fun loadJobs() {
        viewModelScope.launch {
            jobRepository.getJobs()
                .cachedIn(viewModelScope) // Cache the paging data
                .collectLatest { pagingData ->
                    _jobsFlow.value = pagingData
                }
        }
    }
}
