package com.mohsin.cookiefinder.service

import java.io.File

class FileReaderService {

    fun readFile(fileName: String): String {
        val file = File(fileName)
        if (!file.exists()) {
            throw IllegalArgumentException("File not found: $fileName")
        }
        return file.readText()
    }

}
