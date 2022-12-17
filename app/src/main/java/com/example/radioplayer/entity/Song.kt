package com.example.radioplayer.entity

import java.util.Date

data class Song(
    var title: String = "",
    var artist: String = "",
    var year: Int = 1900,
    var album: String = "",
    var coverImageString: String = "",
    var length: String = "00:00",
    var lengthInSeconds: Int = 0,
    var remainingDuration: Int = 0,
    var startDateTime: Date? = null,
    var endDateTime: Date? = null,
    var streamUrl: String = "",
)
