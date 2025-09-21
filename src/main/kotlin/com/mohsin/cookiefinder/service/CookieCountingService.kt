package com.mohsin.cookiefinder.service

import com.mohsin.cookiefinder.model.CookieEntry
import java.time.LocalDate

class CookieCountingService {
    fun findMostActiveCookies(entries: List<CookieEntry>, targetDate: LocalDate): List<String> {
        val entriesForDate = entries.filter { it.timestamp.toLocalDate() == targetDate }

        val counts = mutableMapOf<String, Int>()
        for (entry in entriesForDate) {
            counts[entry.cookie] = counts.getOrDefault(entry.cookie, 0) + 1
        }

        val maxCount = counts.values.maxOrNull() ?: return emptyList()

        return counts.filter { it.value == maxCount }.keys.toList()
    }
}
