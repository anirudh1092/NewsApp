package com.example.newsapp.model.newsApi.dataModel

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NewsArticle(
    val abstract: String?,
    val webUrl: String?,
    val leadParagraph: String?,
    val snippet: String?,
    val multimedia: List<NewsMultimediaModel> = arrayListOf(),
    val headline: NewsHeadlineModel
):Parcelable
