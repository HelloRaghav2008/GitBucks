package com.raghav.gitbucks.data.mappers

import com.raghav.gitbucks.data.dto.CommitActivityRawDto
import com.raghav.gitbucks.data.dto.GitHubUserDto
import com.raghav.gitbucks.data.dto.RepositoryDto
import com.raghav.gitbucks.domain.models.CommitActivityRaw
import com.raghav.gitbucks.domain.models.GitHubUser
import com.raghav.gitbucks.domain.models.Repository

fun GitHubUserDto.toDomain(): GitHubUser = GitHubUser(
    login = login,
    name = name,
    avatarUrl = avatarUrl,
    bio = bio,
    followers = followers,
    following = following,
    publicRepos = publicRepos,
    location = location,
    company = company,
    htmlUrl = htmlUrl,
)

fun RepositoryDto.toDomain(): Repository = Repository(
    id = id,
    name = name,
    fullName = fullName,
    description = description,
    language = language,
    stars = stars,
    forks = forks,
    watchers = watchers,
    htmlUrl = htmlUrl,
    isFork = isFork,
    updatedAt = updatedAt,
)

fun CommitActivityRawDto.toDomain() : CommitActivityRaw = CommitActivityRaw(
    week = week,
    total = total,
    days = days,
)
