package com.orion.templete.ui.bookmark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.orion.templete.data.model.Job
import com.orion.templete.domain.repository.BookmarkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(
    private val bookmarkRepository: BookmarkRepository
) : ViewModel() {

    val bookmarkedJobs = bookmarkRepository.getAllBookmarks()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun bookmarkJob(job: Job) {
        viewModelScope.launch {
            bookmarkRepository.bookmarkJob(job)
        }
    }

    fun removeBookmark(job: Job) {
        viewModelScope.launch {
            bookmarkRepository.removeBookmark(job)
        }
    }

    fun toggleBookmark(job: Job, isCurrentlyBookmarked: Boolean) {
        viewModelScope.launch {
            bookmarkRepository.toggleBookmark(job, isCurrentlyBookmarked)
        }
    }

    fun isJobBookmarked(jobId: Int): Flow<Boolean> {
        return bookmarkRepository.isJobBookmarked(jobId)
    }
}