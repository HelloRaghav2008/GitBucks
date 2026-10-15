package com.raghav.gitbucks.domain.use_cases

import com.raghav.gitbucks.domain.models.Repository

class CalculateContributionUseCase {
    fun execute(repos: List<Repository>): Double =
        repos.filter { !it.isFork }
            .sumOf { repo ->
                (repo.stars * 3) +
                        (repo.forks * 2) +
                        repo.watchers
            }.toDouble()
}
