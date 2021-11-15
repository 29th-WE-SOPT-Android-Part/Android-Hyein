package org.sopt.androidassingment.server

import org.sopt.androidassingment.data.RequestLoginData
import org.sopt.androidassingment.data.ResponseLoginData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface LoginService {
    @Headers("Content-Type: application/json")
    @POST("user/login")
    fun postLogin(
        @Body requestLoginData: RequestLoginData
    ) : Call<ResponseLoginData>
}