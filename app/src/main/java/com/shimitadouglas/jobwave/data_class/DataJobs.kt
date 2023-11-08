package com.shimitadouglas.jobwave.data_class

data class DataJobItem(
    val link: String,
    val organisation: String,
    val title: String
)

data class DataJobResponse(val data: List<DataJobItem>)
