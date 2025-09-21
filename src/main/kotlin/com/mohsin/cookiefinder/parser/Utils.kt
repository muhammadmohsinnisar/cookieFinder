package com.mohsin.cookiefinder.parser

import java.time.LocalDate
import java.time.OffsetDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeParseException

object Utils {

    fun utcDateFromIso(timestamp: String?): LocalDate? {
        if (timestamp == null) return null
        val ts = timestamp.trim()
        if (ts.isEmpty()) return null

        return try {
            val offsetDateTime = OffsetDateTime.parse(ts)
            offsetDateTime.withOffsetSameInstant(ZoneOffset.UTC).toLocalDate()
        } catch (dateTimeParseException: DateTimeParseException) {
            System.err.println("Failed to parse timestamp '$ts': ${dateTimeParseException.message}")
            null
        }
    }
}
