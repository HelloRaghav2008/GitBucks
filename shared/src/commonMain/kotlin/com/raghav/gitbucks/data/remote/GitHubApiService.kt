package com.raghav.gitbucks.data.remote

import com.raghav.gitbucks.data.dto.CommitActivityRawDto
import com.raghav.gitbucks.data.dto.GitHubErrorResponseDto
import com.raghav.gitbucks.data.dto.GitHubUserDto
import com.raghav.gitbucks.data.dto.RepositoryDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText

class GitHubApiService(private val client: HttpClient) {

    private suspend fun HttpResponse.checkGitHubError() {
        val status = this.status.value
        if (status < 400) return
        val msg = if (status in listOf(401, 403, 429)) {
            runCatching { body<GitHubErrorResponseDto>().message }.getOrElse { bodyAsText() }
        } else "GitHub API error $status"
        throw Exception(msg)
    }

    suspend fun getUser(userName: String): Pair<GitHubUserDto, Int> {
        val response: HttpResponse = client.get(urlString = "/users/$userName")
        response.checkGitHubError()
        val remaining = response.headers["X-RateLimit-Remaining"]?.toIntOrNull() ?: 60
        return response.body<GitHubUserDto>() to remaining
    }

    suspend fun getRepos(userName: String): List<RepositoryDto> {
        val response: HttpResponse = client.get(urlString = "/users/$userName/repos") {
            parameter("per_page", 100)
            parameter("sort", "updated")
            parameter("type", "owner")
        }
        response.checkGitHubError()
        return response.body<List<RepositoryDto>>()
    }

    suspend fun getLanguages(owner: String, repo: String): Map<String, Long> {
        val response = client.get(urlString = "/repos/$owner/$repo/languages")
        response.checkGitHubError()
        return response.body()
    }


    suspend fun getCommitActivity(owner: String, repo: String): List<CommitActivityRawDto> {
        val response = client.get(urlString = "/repos/$owner/$repo/stats/commit_activity")
        if (response.status.value == 202) return emptyList() // GitHub is still computing stats
        response.checkGitHubError()
        return response.body()
    }
}
