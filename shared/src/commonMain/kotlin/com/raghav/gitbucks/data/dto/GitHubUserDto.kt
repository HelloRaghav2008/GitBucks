package com.raghav.gitbucks.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GitHubUserDto(
    val login: String,
    val name: String?,
    @SerialName(value = "avatar_url")
    val avatarUrl: String = "",
    val bio: String,
    val followers: Int = 0,
    val following: Int = 0,
    @SerialName("public_repos")
    val publicRepos: Int = 0,
    val location: String? = null,
    val company: String? = null,
    @SerialName("html_url")
    val htmlUrl: String = "",
)
