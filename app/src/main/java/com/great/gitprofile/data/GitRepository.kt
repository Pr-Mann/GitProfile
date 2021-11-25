package com.great.gitprofile.data

import com.great.gitprofile.network.GitHubApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GitRepository @Inject constructor(private val gitHubApi: GitHubApi) {
    suspend fun getSearchResult(query: String): GitUser {
        return gitHubApi.searchUser(query)
    }

    suspend fun getPublicRepos(query: String): List<UserRepos> {
        return gitHubApi.getRepos(query)
    }
}