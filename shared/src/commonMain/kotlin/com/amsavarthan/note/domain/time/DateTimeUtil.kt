package com.amsavarthan.note.domain.time

import kotlinx.datetime.*

object DateTimeUtil {

    fun now(): LocalDateTime {
        return Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
    }

    fun toEpochMillis(dateTime: LocalDateTime): Long {
        return dateTime.toInstant(TimeZone.currentSystemDefault()).toEpochMilliseconds()
    }

    fun toLocalDateTime(epochMilliseconds: Long): LocalDateTime {
        return Instant.fromEpochMilliseconds(epochMilliseconds)
            .toLocalDateTime(TimeZone.currentSystemDefault())
    }

    fun formatAsDate(dateTime: LocalDateTime): String {
        val month = dateTime.month.name.lowercase().take(3).replaceFirstChar { it.uppercase() }
        val day = if (dateTime.dayOfMonth < 10) "0${dateTime.dayOfMonth}" else dateTime.dayOfMonth
        val year = dateTime.year

        return buildString {
            append(month)
            append(" ")
            append(day)
            append(", ")
            append(year)
        }
    }

}