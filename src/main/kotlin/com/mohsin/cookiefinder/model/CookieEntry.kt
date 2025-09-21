package com.mohsin.cookiefinder.model

import java.time.OffsetDateTime

data class CookieEntry(
    val cookie: String,
    val timestamp: OffsetDateTime
)