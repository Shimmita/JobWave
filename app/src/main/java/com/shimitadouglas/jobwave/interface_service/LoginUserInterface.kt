package com.shimitadouglas.jobwave.interface_service

import com.shimitadouglas.jobwave.data_class.DataUserLogin
import com.shimitadouglas.jobwave.data_class.UserCreateResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginUserInterface {
    @POST("loginUser/")
    fun loginUser(
        @Body user: DataUserLogin
    ): Call<UserCreateResponse>

}