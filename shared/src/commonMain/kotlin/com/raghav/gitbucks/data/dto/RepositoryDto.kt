package com.raghav.gitbucks.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RepositoryDto(
    val id: Long = 0,
    val name: String = "",
    @SerialName("full_name")
    val fullName: String = "",
    val description: String? = null,
    val language: String? = null,
    @SerialName("stargazers_count")
    val stars: Int = 0,
    @SerialName("forks_count")
    val forks: Int = 0,
    @SerialName("watchers_count")
    val watchers: Int = 0,
    @SerialName("html_url")
    val htmlUrl: String = "",
    @SerialName("fork")
    val isFork: Boolean = false,
    @SerialName("updated_at")
    val updatedAt: String = "",
)
