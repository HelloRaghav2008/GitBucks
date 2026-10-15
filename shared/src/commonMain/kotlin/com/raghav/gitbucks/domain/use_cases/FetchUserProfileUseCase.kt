package com.raghav.gitbucks.domain.use_cases

import com.raghav.gitbucks.domain.repository.GitHubRepository
import com.raghav.gitbucks.domain.models.GitHubUser

class FetchUserProfileUseCase(
    private val gitHubRepository: GitHubRepository
) {
    suspend fun execute(username: String): Pair<GitHubUser, Int> =
        gitHubRepository.getUser(username)
}
