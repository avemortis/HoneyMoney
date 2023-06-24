package com.vtorushin.utils.datetime

import android.annotation.SuppressLint
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@SuppressLint("NewApi")
fun trimTime(dateAsString: String): String {
    val string = "2023-06-24T14:51:38"
    val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")
    val formatterTo: DateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
    val localDateTime: LocalDateTime = LocalDateTime.parse(dateAsString.take(string.length), formatter)
    return localDateTime.format(formatterTo)
}