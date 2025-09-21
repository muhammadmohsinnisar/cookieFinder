package com.mohsin.cookiefinder.cli

import java.time.LocalDate
import java.time.format.DateTimeParseException

data class CliArguments(
    val fileName: String,
    val date: LocalDate
)

object CliParser {
    fun parseArguments(args: Array<String>): CliArguments {
        val argMap = args.toList().chunked(2).associate { it[0] to it[1] }

        val filename = argMap["-f"] ?: throw IllegalArgumentException("Missing -f <filename>")
        val dateStr = argMap["-d"] ?: throw IllegalArgumentException("Missing -d <date>")

        val date = try {
            LocalDate.parse(dateStr)
        } catch (e: DateTimeParseException) {
            throw IllegalArgumentException("Invalid date format: $dateStr")
        }

        return CliArguments(filename, date)
    }
}
