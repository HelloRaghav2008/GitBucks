package com.raghav.gitbucks.domain.use_cases

import com.raghav.gitbucks.domain.repository.GitHubRepository
import com.raghav.gitbucks.domain.models.LanguageStat
import com.raghav.gitbucks.domain.models.Repository
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

class CalculateLanguageStatsUseCase(private val gitHubRepository: GitHubRepository) {

    suspend fun execute(username: String, repos: List<Repository>): List<LanguageStat> =
        coroutineScope {
            val ownRepos = repos.filter { !it.isFork }.take(30)

            // Fan-out: all language calls fire simultaneously
            val languageMaps: List<Map<String, Long>> = ownRepos
                .map { repo ->
                    async { gitHubRepository.getLanguages(username, repo.name) }
                }
                .awaitAll()

            // Merge: sum bytes per language across all repos
            val merged = mutableMapOf<String, Long>()
            languageMaps.forEach { map ->
                map.forEach { (lang, bytes) ->
                    merged[lang] = (merged[lang] ?: 0L) + bytes
                }
            }

            val total = merged.values.sum().toDouble()
            if (total == 0.0) return@coroutineScope emptyList()

            merged.entries
                .sortedByDescending { it.value }
                .take(8)
                .map { (lang, bytes) ->
                    LanguageStat(
                        language = lang,
                        bytes = bytes.toDouble(),
                        percentage = (bytes / total) * 100.0
                    )
                }
        }
}
