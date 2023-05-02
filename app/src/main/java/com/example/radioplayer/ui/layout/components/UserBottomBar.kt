package com.example.radioplayer.ui.layout.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
fun UserBottomBar(
    onPlaylistClick: () -> Unit,
    onRequestClick: () -> Unit,
    onModerationClick: () -> Unit,
    onInfoClick: () -> Unit,
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
                icon = { Icon(imageVector = Icons.Filled.PlaylistAdd, contentDescription = "Wünsche")},
                label = { Text(text = "Wünsche") }
            )
            BottomNavigationItem(
                selected = false,
                onClick = { onPlaylistClick() },
                icon = { Icon(imageVector = Icons.Filled.LibraryMusic, contentDescription = "Playlist")},
                label = { Text(text = "Playlist") }
            )
            BottomNavigationItem(
                selected = false,
                onClick = { onModerationClick() },
                icon = { Icon(imageVector = Icons.Filled.SupportAgent, contentDescription = "Moderation")},
                label = { Text(text = "Moderation") }
            )
            BottomNavigationItem(
                selected = false,
                onClick = { onInfoClick() },
                icon = { Icon(imageVector = Icons.Filled.Info, contentDescription = "Info")},
                label = { Text(text = "Info") }
            )
        }
    }
}