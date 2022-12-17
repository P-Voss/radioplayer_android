package com.example.radioplayer.network.response

import com.example.radioplayer.entity.Feedback
import com.example.radioplayer.entity.SongRequest
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class DashboardResponse(
    val success: Boolean,
    val moderationFeedback: List<Feedback>,
    val playlistFeedback: List<Feedback>,
    val songRequests: List<SongRequest>
)
