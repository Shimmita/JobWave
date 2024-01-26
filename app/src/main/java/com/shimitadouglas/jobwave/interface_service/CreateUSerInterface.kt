package com.shimitadouglas.jobwave.interface_service

import com.shimitadouglas.jobwave.data_class.DataUserCreate
import com.shimitadouglas.jobwave.data_class.UserCreateResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface CreateUSerInterface {

    @POST("createUser/")
    fun createUser(
        @Body user: DataUserCreate
    ): Call<UserCreateResponse>
}