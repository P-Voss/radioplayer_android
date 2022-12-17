package com.example.radioplayer.entity

import kotlinx.serialization.Serializable

@Serializable
data class Playlist(
    var theme: String = "",
    var moderator: User = User(),
    var recentSongs: List<Song> = mutableListOf()
)
