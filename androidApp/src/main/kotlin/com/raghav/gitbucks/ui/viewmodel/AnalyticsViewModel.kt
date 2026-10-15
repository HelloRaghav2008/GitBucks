package com.raghav.gitbucks.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raghav.gitbucks.domain.use_cases.GetFullAnalyticsUseCase
import com.raghav.gitbucks.ui.state.AnalyticsState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AnalyticsViewModel(
    private val analyticsUseCase: GetFullAnalyticsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<AnalyticsState>(value = AnalyticsState.Idle)
    val uiState = _uiState.asStateFlow()

    fun analyze(userName: String) {
        if (userName.isBlank()) return
        viewModelScope.launch {
            _uiState.value = AnalyticsState.Loading
            _uiState.value = try {
                AnalyticsState.Success(analyticsUseCase.execute(userName))
            } catch (e: Exception) {
                AnalyticsState.Error(e.message ?: "Failed to fetch analytics")
            }
        }
    }

    fun reset() {
        _uiState.value = AnalyticsState.Idle
    }
}
