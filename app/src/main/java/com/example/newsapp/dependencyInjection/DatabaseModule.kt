package com.example.newsapp.dependencyInjection

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import com.example.newsapp.database.NewsArticleDatabase
import com.example.newsapp.database.NewsArticlesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun providesDatabase(@ApplicationContext context: Context): NewsArticleDatabase {
        return Room.databaseBuilder(
            context,
            NewsArticleDatabase::class.java,
            NewsArticleDatabase.DATABASE_NAME,
        ).fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun providesDao(newsDatabase: NewsArticleDatabase): NewsArticlesDao {
        return newsDatabase.newsArticlesDao()
    }
}