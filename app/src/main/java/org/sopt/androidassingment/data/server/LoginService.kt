package org.sopt.androidassingment.data.server

import org.sopt.androidassingment.data.request.RequestLoginData
import org.sopt.androidassingment.data.response.ResponseLoginData
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