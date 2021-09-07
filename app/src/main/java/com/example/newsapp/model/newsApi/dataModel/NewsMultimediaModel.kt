package com.example.newsapp.model.newsApi.dataModel

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NewsMultimediaModel(
    var caption: String?,
    var imageUrl: String?
): Parcelable
