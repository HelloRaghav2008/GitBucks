package com.raghav.gitbucks

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform