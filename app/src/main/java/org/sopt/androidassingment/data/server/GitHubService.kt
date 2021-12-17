package org.sopt.androidassingment.data.server

import org.sopt.androidassingment.data.response.ResponseFollowerData
import org.sopt.androidassingment.data.response.ResponseRepoData
import org.sopt.androidassingment.data.response.ResponseUserData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubService {
    @GET("/users/{username}/followers")
    fun getFollowers(
        @Path("username") username : String
    ) : Call<List<ResponseFollowerData>>

    @GET("users/{username}")
    fun getUsers(
        @Path("username") username : String
    ) : Call<ResponseUserData>

    @GET("users/{username}/repos")
    fun getRepos(
        @Path("username") username : String
    ) : Call<List<ResponseRepoData>>
}