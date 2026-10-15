package com.raghav.gitbucks.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class CommitActivityRawDto(
    val week: Long = 0,
    val total: Int = 0,
    val days: List<Int> = emptyList(),
)
