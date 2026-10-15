package com.raghav.gitbucks.domain.models

data class GitHubUser(
    val login: String,
    val name: String?,
    val avatarUrl: String = "",
    val bio: String,
    val followers: Int = 0,
    val following: Int = 0,
    val publicRepos: Int = 0,
    val location: String? = null,
    val company: String? = null,
    val htmlUrl: String = "",
)
