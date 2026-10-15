package com.raghav.gitbucks.di

import com.raghav.gitbucks.domain.use_cases.CalculateContributionUseCase
import com.raghav.gitbucks.domain.use_cases.CalculateLanguageStatsUseCase
import com.raghav.gitbucks.domain.use_cases.FetchCommitTrendUseCase
import com.raghav.gitbucks.domain.use_cases.FetchUserProfileUseCase
import com.raghav.gitbucks.domain.use_cases.GetFullAnalyticsUseCase
import org.koin.dsl.module
import org.koin.plugin.module.dsl.single

val domainModule = module {
    includes(dataModule)
    single<CalculateContributionUseCase>()
    single<CalculateLanguageStatsUseCase>()
    single<FetchCommitTrendUseCase>()
    single<FetchUserProfileUseCase>()
    single<GetFullAnalyticsUseCase>()
}
