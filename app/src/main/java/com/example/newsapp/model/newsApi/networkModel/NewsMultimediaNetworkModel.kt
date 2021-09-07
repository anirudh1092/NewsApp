package com.example.newsapp.model.newsApi.networkModel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class NewsMultimediaNetworkModel(
    @SerializedName("caption")
    @Expose
    var caption: String,

    @SerializedName("url")
    @Expose
    var imageUrl: String
)
