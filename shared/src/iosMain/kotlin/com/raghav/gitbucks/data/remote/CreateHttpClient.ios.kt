package com.raghav.gitbucks.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.engine.darwin.Darwin
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

actual fun createHttpClient(): HttpClient = HttpClient(engineFactory = Darwin) {
    install(plugin = HttpTimeout) {
        requestTimeoutMillis = 3_000
        connectTimeoutMillis = 3_000
        socketTimeoutMillis = 3_000
    }
    install(plugin = ContentNegotiation) {
        json(Json {
            ignoreUnknownKeys = true
            prettyPrint = true
            isLenient = true
        })
    }
    defaultRequest {
        url(urlString = "https://api.github.com")
    }
}
