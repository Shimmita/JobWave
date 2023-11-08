package com.shimitadouglas.jobwave.interface_service

import com.shimitadouglas.jobwave.data_class.DataJobResponse
import retrofit2.Call
import retrofit2.http.GET

interface GetAllPostInterface {

    @GET("/jobsApi/category/getJobs/uPuQJjkAohnQJUJGdcXL5r0H")
    fun getAllJobs(): Call<DataJobResponse>
}

