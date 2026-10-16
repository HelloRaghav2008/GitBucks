package com.raghav.gitbucks.domain.models

data class Repository(
    val id: Long = 0,
    val name: String = "",
    val fullName: String = "",
    val description: String? = null,
    val language: String? = null,
    val stars: Int = 0,
    val forks: Int = 0,
    val watchers: Int = 0,
    val htmlUrl: String = "",
    val isFork: Boolean = false,
    val updatedAt: String = "",
)
