package com.raghav.gitbucks

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.raghav.gitbucks.di.appModule
import com.raghav.gitbucks.ui.screens.AnalyticsScreen
import com.raghav.gitbucks.ui.screens.ErrorScreen
import com.raghav.gitbucks.ui.screens.LoadingScreen
import com.raghav.gitbucks.ui.screens.SearchScreen
import com.raghav.gitbucks.ui.state.AnalyticsState
import com.raghav.gitbucks.ui.viewmodel.AnalyticsViewModel
import org.koin.compose.KoinApplicationPreview
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun App(
    viewModel: AnalyticsViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    MaterialTheme {
        when (val current = uiState) {
            is AnalyticsState.Idle -> SearchScreen(onSearch = { viewModel.analyze(it) })
            is AnalyticsState.Loading -> LoadingScreen()
            is AnalyticsState.Success -> AnalyticsScreen(
                result = current.result,
                onBack = { viewModel.reset() })

            is AnalyticsState.Error -> ErrorScreen(
                message = current.message,
                onRetry = { viewModel.reset() }
            )
        }
    }

}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun AppPreview() {
    KoinApplicationPreview(application = { modules(appModule) }) {
        App()
    }
}
