package com.mohsin.cookiefinder.parser

import com.mohsin.cookiefinder.model.CookieEntry
import java.time.LocalDate
import java.time.OffsetDateTime

class CookieLogParser {

    fun parseLine(line: String): CookieEntry {
        val parts = line.split(",")
        val cookie = parts[0]
        val timestamp = OffsetDateTime.parse(parts[1])
        return CookieEntry(cookie, timestamp)
    }

    fun parse(csv: String): List<CookieEntry> {
        return csv.lines()
            .drop(1) // skip header
            .filter { it.isNotBlank() }
            .map { parseLine(it) }
    }
}
