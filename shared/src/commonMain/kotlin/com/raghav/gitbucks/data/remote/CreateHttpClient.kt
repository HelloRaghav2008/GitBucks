package com.raghav.gitbucks.data.remote

import io.ktor.client.HttpClient

expect fun createHttpClient(): HttpClient
