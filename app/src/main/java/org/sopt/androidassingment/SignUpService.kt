package org.sopt.androidassingment

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface SignUpService {
    @Headers("Content-Type: application/json")
    @POST("user/signup")
    fun postSignUp(
        @Body requestSingUpData: RequestSignUpData
    ) : Call<ResponseSignUpData>
}