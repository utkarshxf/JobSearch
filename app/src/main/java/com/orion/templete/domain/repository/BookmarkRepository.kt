package com.orion.templete.domain.repository


import com.flashcall.me.data.local.dao.JobDao
import com.flashcall.me.data.local.mapper.JobMapper.toEntity
import com.flashcall.me.data.local.mapper.JobMapper.toJob
import com.orion.templete.data.model.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BookmarkRepository @Inject constructor(
    private val jobDao: JobDao
) {
    // Add job to bookmarks
    suspend fun bookmarkJob(job: Job) {
        jobDao.insertBookmark(job.toEntity())
    }
    
    // Remove job from bookmarks
    suspend fun removeBookmark(job: Job) {
        jobDao.deleteBookmarkById(job.id)
    }
    
    // Toggle bookmark status
    suspend fun toggleBookmark(job: Job, isBookmarked: Boolean) {
        if (isBookmarked) {
            removeBookmark(job)
        } else {
            bookmarkJob(job)
        }
    }
    
    // Check if a job is bookmarked
    fun isJobBookmarked(jobId: Int): Flow<Boolean> {
        return jobDao.isJobBookmarked(jobId)
    }
    
    // Get all bookmarked jobs
    fun getAllBookmarks(): Flow<List<Job>> {
        return jobDao.getAllBookmarks().map { entities ->
            entities.map { it.toJob() }
        }
    }
}