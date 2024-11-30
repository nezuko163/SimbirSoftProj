package com.nezuko.businessdetails.utils

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

fun parseLocalDateTimeToDate(date: LocalDateTime): String {
    val formatter = DateTimeFormatter.ofPattern("EE, dd MMM, yyyy", Locale("ru"))
    return date.format(formatter)
}

fun parseLocalDateTimeToTime(date: LocalDateTime): String {
    val formatter = DateTimeFormatter.ofPattern("hh:mm", Locale("ru"))
    return date.format(formatter)
}

fun convertMillisToDate(millis: Long): LocalDate {
    return LocalDateTime.
}