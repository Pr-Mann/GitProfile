package com.great.gitprofile.network

import com.great.gitprofile.data.GitUser
import com.great.gitprofile.data.UserRepos
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubApi {
    companion object {
        const val BASE_URL = "https://api.github.com/"
    }

    @GET("users/{userId}")
    suspend fun searchUser(
        @Path("userId") userId: String
    ): GitUser

    @GET("users/{userId}/repos")
    suspend fun getRepos(
        @Path("userId") userId: String
    ): List<UserRepos>
}