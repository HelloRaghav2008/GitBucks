package com.raghav.gitbucks.di

import com.raghav.gitbucks.data.remote.GitHubApiService
import com.raghav.gitbucks.data.remote.createHttpClient
import com.raghav.gitbucks.data.repository.AnalyticsCache
import com.raghav.gitbucks.data.repository.GitHubRepositoryImpl
import com.raghav.gitbucks.domain.repository.GitHubRepository
import io.ktor.client.HttpClient
import org.koin.dsl.bind
import org.koin.dsl.module
import org.koin.plugin.module.dsl.single

val dataModule = module {
    single<HttpClient> { createHttpClient() }
    single<GitHubApiService>()
    single<GitHubRepositoryImpl>() bind GitHubRepository::class
    single<AnalyticsCache>()
}
