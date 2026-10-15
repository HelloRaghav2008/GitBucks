package com.raghav.gitbucks.data.repository

import com.raghav.gitbucks.data.mappers.toDomain
import com.raghav.gitbucks.data.remote.GitHubApiService
import com.raghav.gitbucks.domain.repository.GitHubRepository
import com.raghav.gitbucks.domain.models.CommitActivityRaw
import com.raghav.gitbucks.domain.models.GitHubUser
import com.raghav.gitbucks.domain.models.Repository

class GitHubRepositoryImpl(
    private val gitHubApiService: GitHubApiService
) : GitHubRepository {
    override suspend fun getUser(userName: String): Pair<GitHubUser, Int> {
        val (dto, remaining) = gitHubApiService.getUser(userName = userName)
        return dto.toDomain() to remaining
    }

    override suspend fun getRepos(userName: String): List<Repository> =
        gitHubApiService.getRepos(userName = userName).map { it.toDomain() }

    override suspend fun getLanguages(
        owner: String,
        repo: String
    ): Map<String, Long> =
        runCatching {
            gitHubApiService.getLanguages(owner = owner, repo = repo)
        }.getOrDefault(defaultValue = emptyMap())

    override suspend fun getCommitActivity(
        owner: String,
        repo: String
    ): List<CommitActivityRaw> =
        runCatching {
            gitHubApiService.getCommitActivity(
                owner = owner,
                repo = repo
            ).map { it.toDomain() }
        }.getOrDefault(
            defaultValue = emptyList()
        )
}
