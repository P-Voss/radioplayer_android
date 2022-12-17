package com.example.radioplayer.entity

import java.text.SimpleDateFormat
import java.util.*

data class SongRequest(
    val title: String = "",
    val artist: String = "",
    val username: String = "",
    val comment: String = "",
    val datetime: Date = Date()
)
{
    fun getFormattedDate(): String {
        val dateFormat = SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.GERMANY)
        return dateFormat.format(datetime)
    }
}

