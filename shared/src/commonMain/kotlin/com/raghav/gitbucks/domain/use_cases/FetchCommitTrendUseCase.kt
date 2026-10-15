package com.raghav.gitbucks.domain.use_cases

import com.raghav.gitbucks.data.remote.formatEpochToYearMonth
import com.raghav.gitbucks.domain.repository.GitHubRepository
import com.raghav.gitbucks.domain.models.CommitActivity
import com.raghav.gitbucks.domain.models.Repository
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

class FetchCommitTrendUseCase(
    private val gitHubRepository: GitHubRepository
) {
    suspend fun execute(username: String, repos: List<Repository>): List<CommitActivity> =
        coroutineScope {
            val top5 = repos.filter { !it.isFork }
                .sortedByDescending { it.stars }
                .take(n = 5)

            // Parallel fetch for all top repos
            val allWeeklyData = top5
                .map { repo ->
                    async { gitHubRepository.getCommitActivity(owner = username, repo.name) }
                }
                .awaitAll()
                .flatten()

            // Aggregate by month across all repos
            val monthlyTotals = mutableMapOf<String, Int>()
            allWeeklyData.forEach { weekly ->
                if (weekly.total > 0) {
                    val monthKey = formatEpochToYearMonth(epochSeconds = weekly.week)
                    monthlyTotals[monthKey] = (monthlyTotals[monthKey] ?: 0) + weekly.total
                }
            }

            // Return last 12 months sorted chronologically
            monthlyTotals.entries
                .sortedBy { it.key }
                .takeLast(n = 12)
                .map { CommitActivity(month = it.key, count = it.value) }
        }
}
