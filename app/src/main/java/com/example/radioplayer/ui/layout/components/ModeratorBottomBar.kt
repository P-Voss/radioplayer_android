package com.example.radioplayer.ui.layout.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
fun ModeratorBottomBar(
    onModerationFeedbackClick: () -> Unit,
    onPlaylistFeedbackClick: () -> Unit,
    onRequestClick: () -> Unit,
) {

    BottomAppBar(
        elevation = 12.dp,
        cutoutShape = CircleShape,
        backgroundColor = MaterialTheme.colors.secondary
    ) {
        Row(
            horizontalArrangement = Arrangement.Start
        ) {
            BottomNavigationItem(
                selected = false,
                onClick = { onRequestClick() },
                icon = { Icon(imageVector = Icons.Filled.QueueMusic, contentDescription = "Wünsche") },
                label = { Text(text = "Wünsche") }
            )
            BottomNavigationItem(
                selected = false,
                onClick = { onPlaylistFeedbackClick() },
                icon = { Icon(imageVector = Icons.Filled.StarRate, contentDescription = "Playlist") },
                label = { Text(text = "Playlist") }
            )
            BottomNavigationItem(
                selected = false,
                onClick = { onModerationFeedbackClick() },
                icon = { Icon(imageVector = Icons.Filled.StarRate, contentDescription = "Moderation") },
                label = { Text(text = "Moderation") }
            )
            BottomNavigationItem(
                selected = false,
                onClick = { onModerationFeedbackClick() },
                icon = { Icon(imageVector = Icons.Filled.Info, contentDescription = "Info") },
                label = { Text(text = "Info") },
                enabled = false
            )
        }
    }
}