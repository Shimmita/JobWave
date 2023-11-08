package com.shimitadouglas.jobwave.interface_service

import com.shimitadouglas.jobwave.data_class.DataUserCreate
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface CreateUSerInterface {

    @FormUrlEncoded
    @POST("/createUser")
    fun createUser(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<DataUserCreate>
}