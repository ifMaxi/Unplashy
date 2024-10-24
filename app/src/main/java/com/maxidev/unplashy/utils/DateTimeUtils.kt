package com.maxidev.unplashy.utils

import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

object DateTimeUtils {

    fun dateTime(input: String): ZonedDateTime =
        ZonedDateTime.parse(input, DateTimeFormatter.ISO_DATE_TIME)

    val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm")
}