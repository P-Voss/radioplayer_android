package com.example.radioplayer.entity

import java.util.Date

data class Song(
    val name: String,
    val artist: String,
    val releaseDate: Date,
    val album: String,
    val coverImageString: String,
    val lengthInSeconds: String,
    val startDateTime: Date,
)
