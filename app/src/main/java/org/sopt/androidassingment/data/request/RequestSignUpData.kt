package org.sopt.androidassingment.data.request

import com.google.gson.annotations.SerializedName

data class RequestSignUpData(
    @SerializedName("email")
    val id:String,
    val name:String,
    val password:String
)
