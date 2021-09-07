package com.example.newsapp.model.newsApi.dataModel

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NewsHeadlineModel(
    val headlineMain: String?,
    val headlineName: String?,
    val printHeadline: String?
) : Parcelable
