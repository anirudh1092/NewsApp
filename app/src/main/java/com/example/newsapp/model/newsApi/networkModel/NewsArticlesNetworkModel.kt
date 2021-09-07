package com.example.newsapp.model.newsApi.networkModel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class NewsArticlesNetworkModel(
    @SerializedName("response")
    @Expose
    val response : NewsDocsNetworkModel
)
