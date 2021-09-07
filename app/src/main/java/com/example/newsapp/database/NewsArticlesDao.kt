package com.example.newsapp.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface NewsArticlesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveNewsArticlesData(newsArticleEntity: NewsArticleEntity)

    @Update
    suspend fun updateNewsArticleData(newsArticleEntity: NewsArticleEntity)

    @Delete
    suspend fun deleteNewsArticleData(newsArticleEntity: NewsArticleEntity)

    @Query("SELECT * from NewsArticleEntity where `query` like :query ")
    fun getBusinessDataForId(query: String): NewsArticleEntity
}