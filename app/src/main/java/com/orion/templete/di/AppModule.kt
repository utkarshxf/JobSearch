package com.orion.templete.di

import android.content.Context
import androidx.room.Room
import com.flashcall.me.data.local.AppDatabase
import com.flashcall.me.data.local.dao.JobDao
import com.orion.templete.data.network.ApiService
import com.orion.templete.data.network.ApiService.Companion.baseurl
import com.orion.templete.data.repository.JobRepositoryImplementation
import com.orion.templete.domain.repository.JobRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object  AppModule {
    @Provides
    fun provideContext(@ApplicationContext context: Context): Context {
        return context
    }

    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return Retrofit.Builder()
            .baseUrl(baseurl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideJobDao(appDatabase: AppDatabase): JobDao {
        return appDatabase.jobDao()
    }
    @Provides
    fun provideJobsRepository(
    apiService: ApiService,
    @ApplicationContext context: Context
    ): JobRepository {
        return JobRepositoryImplementation(apiService = apiService , context = context)
    }
}