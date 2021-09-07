package com.example.newsapp.model.newsApi.dataModel

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NewsArticlesModel(
    val response: NewsDocsModel
): Parcelable
