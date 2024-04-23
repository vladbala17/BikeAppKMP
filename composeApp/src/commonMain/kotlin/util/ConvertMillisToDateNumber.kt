package util

import kotlinx.datetime.Instant
import kotlinx.datetime.format
import kotlinx.datetime.format.DateTimeComponents
import kotlinx.datetime.format.byUnicodePattern

fun convertMillisToDateMonthNumber(millis: Long): String {
    val formatPattern = "dd.MM.yyyy"
    return Instant.fromEpochMilliseconds(millis).format(DateTimeComponents.Format {
        byUnicodePattern(formatPattern)
    })
}

fun convertMonthNumberToMonthName(month: Int): String {
    return when (month) {
        1 -> "January"
        2 -> "February"
        3 -> "March"
        4 -> "April"
        5 -> "May"
        6 -> "June"
        7 -> "July"
        8 -> "August"
        9 -> "September"
        10 -> "October"
        11 -> "November"
        12 -> "December"
        else -> "June"
    }
}