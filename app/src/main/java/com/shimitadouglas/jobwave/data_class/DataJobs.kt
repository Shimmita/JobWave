package com.shimitadouglas.jobwave.data_class

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataJobItem(
    val link: String,
    val organisation: String,
    val title: String
) : Parcelable

data class DataJobResponse(val data: List<DataJobItem>)
