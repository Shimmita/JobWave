package com.shimitadouglas.jobwave.data_class

import com.google.gson.annotations.SerializedName
import java.util.Date

data class UserCreateResponse(val message: String, val data: User)


data class User(
    @SerializedName("_id")
    val id: String,
    val name: String,
    val phone: String,
    val github: String,
    val linkedin: String,
    val email: String,
    val key: String,
    val token: String,
    val isAdmin: Boolean,
    val isVerified: Boolean,
    val createdAt: Date,
    val updatedAt: Date
)
