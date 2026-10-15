package com.raghav.gitbucks.domain.repository

import com.raghav.gitbucks.domain.models.CommitActivityRaw
import com.raghav.gitbucks.domain.models.GitHubUser
import com.raghav.gitbucks.domain.models.Repository

interface GitHubRepository {
    suspend fun getUser(userName: String): Pair<GitHubUser, Int>

    suspend fun getRepos(userName: String): List<Repository>

    suspend fun getLanguages(owner: String, repo: String): Map<String, Long>

    suspend fun getCommitActivity(owner: String, repo: String): List<CommitActivityRaw>
}
