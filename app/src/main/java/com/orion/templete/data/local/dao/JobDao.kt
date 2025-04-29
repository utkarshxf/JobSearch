package com.flashcall.me.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.flashcall.me.data.local.entity.JobEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface JobDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBookmark(job: JobEntity)

    @Delete
    suspend fun deleteBookmark(job: JobEntity)

    @Query("SELECT * FROM bookmarks")
    fun getAllBookmarks(): Flow<List<JobEntity>>

    @Query("SELECT * FROM bookmarks WHERE id = :jobId")
    suspend fun getBookmarkById(jobId: Int): JobEntity?

    @Query("SELECT EXISTS(SELECT 1 FROM bookmarks WHERE id = :jobId)")
    fun isJobBookmarked(jobId: Int): Flow<Boolean>

    @Query("DELETE FROM bookmarks WHERE id = :jobId")
    suspend fun deleteBookmarkById(jobId: Int)
}
