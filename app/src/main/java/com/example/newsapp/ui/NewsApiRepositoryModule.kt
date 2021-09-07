package com.example.newsapp.ui

import com.example.newsapp.api.NewsApiService
import com.example.newsapp.database.NewsArticlesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NewsApiRepositoryModule {

    @Singleton
    @Provides
    fun providesRepository(service: NewsApiService, dao: NewsArticlesDao): NewApiRepository {
        return NewApiRepository(service, dao)
    }
}