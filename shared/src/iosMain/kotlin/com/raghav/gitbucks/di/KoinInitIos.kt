package com.raghav.gitbucks.di

import org.koin.core.context.startKoin

fun initKoinIos() {
    startKoin {
        modules(dataModule, domainModule)
    }
}
