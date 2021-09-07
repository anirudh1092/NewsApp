package com.example.newsapp.api

import com.example.newsapp.model.newsApi.networkModel.NewsArticlesNetworkModel
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    @GET("articlesearch.json")
    suspend fun getNewsArticles(@Query("q") queryInput: String, @Query("api-key") apiKey: String): NewsArticlesNetworkModel
}