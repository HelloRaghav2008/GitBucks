package com.raghav.gitbucks.di

import com.raghav.gitbucks.ui.viewmodel.AnalyticsViewModel
import org.koin.dsl.module
import org.koin.plugin.module.dsl.viewModel

val uiModule = module {
    includes(domainModule)
    viewModel<AnalyticsViewModel>()
}
