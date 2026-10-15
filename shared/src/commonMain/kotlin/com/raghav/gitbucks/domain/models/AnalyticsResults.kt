package com.raghav.gitbucks.domain.models

data class AnalyticsResults(
    val user: GitHubUser,
    val topRepositories: List<Repository>,
    val languageStat: List<LanguageStat>,
    val contributionScore: Double,
    val commitTrend: List<CommitActivity>,
    val rateLimitRemaining: Int,
    val rateLimitTotal: Int,
)
