package com.mohsin.cookiefinder

import com.mohsin.cookiefinder.cli.CliParser
import com.mohsin.cookiefinder.service.CookieCountingService
import com.mohsin.cookiefinder.parser.CookieLogParser
import com.mohsin.cookiefinder.service.FileReaderService
import java.io.File
import java.time.LocalDate
import kotlin.system.exitProcess

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main(args: Array<String>) {
    try {
        val cliArguments = CliParser.parseArguments(args)
        val file = FileReaderService().readFile(cliArguments.fileName)
        val entries = CookieLogParser().parse(file)
        val mostActiveCookie = CookieCountingService().findMostActiveCookies(entries, cliArguments.date)

        if (mostActiveCookie.isEmpty()) {
            println("No cookies found for date ${cliArguments.date}")
        } else {
            mostActiveCookie.forEach { println(it) }
        }

    } catch (e: Exception) {
        System.err.println("Error: ${e.message}")
        println("Usage: cookieFinder -f <filename> -d <date>")
        println("Example: cookieFinder -f cookie_log.csv -d 2018-12-09")
        exitProcess(1)
    }
}