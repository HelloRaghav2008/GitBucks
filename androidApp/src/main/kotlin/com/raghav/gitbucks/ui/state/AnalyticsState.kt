package com.raghav.gitbucks.ui.state

import com.raghav.gitbucks.domain.models.AnalyticsResults

sealed interface AnalyticsState {
    object Idle : AnalyticsState
    object Loading : AnalyticsState
    data class Success(val result: AnalyticsResults) : AnalyticsState
    data class Error(val message: String) : AnalyticsState
}
