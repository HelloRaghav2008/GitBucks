package com.raghav.gitbucks.domain.models

data class CommitActivityRaw(
    val week: Long,
    val total: Int,
    val days: List<Int>,
)
