package com.example.newsapp.model.newsApi.networkModel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class NewsHeadlineNetworkModel(
    @SerializedName("main")
    @Expose
    val headlineMain: String,

    @SerializedName("name")
    @Expose
    val headlineName: String,

    @SerializedName("print_headline")
    @Expose
    val printHeadline: String
)
