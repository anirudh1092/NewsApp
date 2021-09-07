package com.example.newsapp.database

import androidx.room.TypeConverter
import com.example.newsapp.model.newsApi.dataModel.NewsArticlesModel
import com.example.newsapp.model.newsApi.networkModel.NewsArticlesNetworkModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class Converters {

    @TypeConverter
    fun newsArticleToString(newsArticlesNetworkModel: NewsArticlesModel): String {
        val gson = Gson()
        return gson.toJson(newsArticlesNetworkModel)
    }

    @TypeConverter
    fun stringToNewsArticlesModel(jsonString: String): NewsArticlesModel {
        val listType = object : TypeToken<NewsArticlesModel>() {}.type
        return Gson().fromJson(jsonString, listType)
    }
}