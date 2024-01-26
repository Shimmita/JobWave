package com.shimitadouglas.jobwave.utilities

import java.util.regex.Pattern

object Validations {
    //check if is valid url
    fun isValidUrl(url: String): Boolean {
        val regex = "^(http|https?://)?([a-zA-Z0-9]+\\.)+[a-zA-Z0-9]+(/.*|)$"
        val matcher = Pattern.compile(regex).matcher(url)
        return matcher.matches()
    }


    //check valid email
    fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    //is valid phone number
    fun isValidPhone(phone: String): Boolean {
        return android.util.Patterns.PHONE.matcher(phone).matches()
    }


}