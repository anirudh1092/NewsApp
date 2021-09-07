package com.example.newsapp.model.newsApi.networkModel

import com.example.newsapp.model.newsApi.dataModel.NewsHeadlineModel
import com.example.newsapp.model.newsApi.dataModel.NewsMultimediaModel
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class NewsArticleNetworkModel(
    @SerializedName("abstract")
    @Expose
    val abstract: String,

    @SerializedName("web_url")
    @Expose
    val webUrl: String,

    @SerializedName("lead_paragraph")
    @Expose
    val leadParagraph: String,

    @SerializedName("snippet")
    @Expose
    val snippet: String,

    @SerializedName("multimedia")
    @Expose
    val multimedia: List<NewsMultimediaNetworkModel>,

    @SerializedName("headline")
    @Expose
    val headline: NewsHeadlineNetworkModel
)
