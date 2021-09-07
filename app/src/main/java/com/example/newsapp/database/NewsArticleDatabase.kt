package com.example.newsapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [NewsArticleEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class NewsArticleDatabase() : RoomDatabase() {
    abstract fun newsArticlesDao(): NewsArticlesDao

    companion object {
        val DATABASE_NAME = "business_data_database"
    }
}