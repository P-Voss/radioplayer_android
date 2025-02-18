package com.example.radioplayer.entity

import java.text.SimpleDateFormat
import java.util.*

data class Feedback(
    val score: Int = 1,
    val comment: String = "",
    val username: String = "",
    val datetime: Date = Date()
)
{
    fun getFormattedDate(): String {
        val dateFormat = SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.GERMANY)
        return dateFormat.format(datetime)
    }
}
