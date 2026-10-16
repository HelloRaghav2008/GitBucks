package com.raghav.gitbucks.di

import com.raghav.gitbucks.domain.use_cases.GetFullAnalyticsUseCase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class KoinHelper : KoinComponent {
    val getFullAnalyticsUseCase: GetFullAnalyticsUseCase by inject()
}
