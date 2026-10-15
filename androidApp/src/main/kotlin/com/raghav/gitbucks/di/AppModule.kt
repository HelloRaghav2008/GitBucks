package com.raghav.gitbucks.di

import org.koin.dsl.module

val appModule = module {
    includes(dataModule, domainModule, uiModule)
}
