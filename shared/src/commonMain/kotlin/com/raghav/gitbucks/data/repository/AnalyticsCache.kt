package com.raghav.gitbucks.data.repository

import com.raghav.gitbucks.domain.models.AnalyticsResults

class AnalyticsCache {
    private data class CacheEntry(val result: AnalyticsResults, val insertedAt: Long)

    private val cache = mutableMapOf<String, CacheEntry>()
    private val ttlMs = 5 * 60 * 1000L
    fun get(userName: String): AnalyticsResults? {
        val entry = cache[userName.lowercase()] ?: return null
        return if (0L - entry.insertedAt < ttlMs) entry.result else {
            cache.remove(key = userName.lowercase())
            null
        }
    }

    fun put(userName: String, result: AnalyticsResults) {
        cache[userName.lowercase()] = CacheEntry(result, insertedAt = 0L)
    }
}
