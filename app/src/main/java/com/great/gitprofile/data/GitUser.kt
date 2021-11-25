package com.great.gitprofile.data

data class GitUser(
    val name: String,
    val avatar_url: String
)

data class UserRepos(
    val name: String,
    val description: String,
    val updated_at: String,
    val stargazers_count: Int,
    val forks: Int
)