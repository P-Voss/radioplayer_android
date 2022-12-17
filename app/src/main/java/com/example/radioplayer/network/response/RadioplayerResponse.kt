package com.example.radioplayer.network.response

import com.example.radioplayer.entity.Playlist
import com.example.radioplayer.entity.Song
import kotlinx.serialization.Serializable

@Serializable
data class RadioplayerResponse(
    val success: Boolean,
    val playlist: Playlist,
    val currentSong: Song
)
