package com.example.radioplayer.ui.layout.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LibraryMusic
import androidx.compose.material.icons.filled.PlaylistAdd
import androidx.compose.material.icons.filled.QueueMusic
import androidx.compose.material.icons.filled.StarRate
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun UserBottomBar(
    onPlaylistClick: () -> Unit,
    onRequestClick: () -> Unit,
    onFeedbackClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .background(color = MaterialTheme.colors.secondary)
            .padding(12.dp)
            .fillMaxHeight(0.1f),
        verticalAlignment = Alignment.CenterVertically
    ) {

        BottomBarButton(
            icon = Icons.Filled.PlaylistAdd,
            label = "Wünsche",
            onClick = onRequestClick
        )

        Spacer(modifier = Modifier.weight(1f))

        BottomBarButton(
//            icon = Icons.Filled.QueueMusic,
            icon = Icons.Filled.LibraryMusic,
            label = "Playlist",
            onClick = onPlaylistClick
        )

        Spacer(modifier = Modifier.weight(1f))

        BottomBarButton(
            icon = Icons.Filled.StarRate,
            label = "Bewertung",
            onClick = onFeedbackClick
        )
    }
}