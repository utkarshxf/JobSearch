package com.flashcall.me.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.flashcall.me.data.local.dao.JobDao
import com.flashcall.me.data.local.entity.JobEntity

@Database(
    entities = [
        JobEntity::class,
        ],
    version = 1
)
@TypeConverters()
abstract class AppDatabase : RoomDatabase() {
    abstract fun jobDao(): JobDao

    companion object {
        const val DATABASE_NAME = "bookmark_database"
    }
}