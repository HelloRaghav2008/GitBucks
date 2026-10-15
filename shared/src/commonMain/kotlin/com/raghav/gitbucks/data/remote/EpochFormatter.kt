package com.raghav.gitbucks.data.remote

import kotlinx.datetime.TimeZone
import kotlinx.datetime.number
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Instant

fun formatEpochToYearMonth(epochSeconds: Long): String {
    val instantFromMillis = Instant.fromEpochMilliseconds(epochSeconds)
    val localDateTime = instantFromMillis.toLocalDateTime(TimeZone.UTC)

    val year = localDateTime.year
    val month = localDateTime.month.number.toString().padStart(length = 2, padChar = '0')
    return "$year-$month"
}
