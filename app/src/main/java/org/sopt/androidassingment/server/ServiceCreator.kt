package org.sopt.androidassingment.server

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceCreator {
    private const val BASE_URL = "https://asia-northeast3-we-sopt-29.cloudfunctions.net/api/"
    private const val GITHUB_URL = "https://api.github.com/"

    private val retrofit : Retrofit = Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val retrofit_git : Retrofit = Retrofit
        .Builder()
        .baseUrl(GITHUB_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val loginService: LoginService = retrofit.create(LoginService::class.java)
    val singUpService : SignUpService = retrofit.create(SignUpService::class.java)

    val gitHubService : GitHubService = retrofit_git.create(GitHubService::class.java)
}