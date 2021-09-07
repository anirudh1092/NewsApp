package com.example.newsapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.newsapp.model.newsApi.dataModel.NewsArticlesModel

@Entity
data class NewsArticleEntity(

    @ColumnInfo
    @PrimaryKey
    val query: String,

    @ColumnInfo
    val newsArticlesModel: NewsArticlesModel

)