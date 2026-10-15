package com.raghav.gitbucks.domain.use_cases

import com.raghav.gitbucks.data.repository.AnalyticsCache
import com.raghav.gitbucks.domain.repository.GitHubRepository
import com.raghav.gitbucks.domain.models.AnalyticsResults
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

class GetFullAnalyticsUseCase(
    private val gitHubRepository: GitHubRepository,
    private val cache: AnalyticsCache,
    private val fetchProfile: FetchUserProfileUseCase,
    private val langStats: CalculateLanguageStatsUseCase,
    private val scoreCalc: CalculateContributionUseCase,
    private val commitTrend: FetchCommitTrendUseCase,
) {
    suspend fun execute(userName: String): AnalyticsResults {
        cache.get(userName)?.let { return it }
        return coroutineScope {
            val (user, rateLimit) = fetchProfile.execute(userName)
            val repos = gitHubRepository.getRepos(userName)

            // Language stats and commit trend fire simultaneously
            val languageDeferred = async { langStats.execute(userName, repos) }
            val trendDeferred    = async { commitTrend.execute(userName, repos) }

            val languages  = languageDeferred.await()
            val trend      = trendDeferred.await()
            val score: Double = scoreCalc.execute(repos)
            val topRepos   = repos.sortedByDescending { it.stars }.take(n = 10)

            AnalyticsResults(
                user = user,
                topRepositories = topRepos,
                languageStat = languages,
                contributionScore = score,
                commitTrend = trend,
                rateLimitRemaining = rateLimit,
                rateLimitTotal = 60
            ).also { cache.put(userName, result = it) }
        }
    }
}
