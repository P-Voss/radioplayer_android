package com.example.radioplayer.ui.layout.screen.user

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarOutline
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.radioplayer.entity.Feedback
import com.example.radioplayer.entity.Playlist
import com.example.radioplayer.entity.Song
import com.example.radioplayer.ui.layout.screen.moderator.FeedbackEntry


@Composable
fun PlaylistScreen(playlist: Playlist) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.9f)
            .padding(12.dp),
        backgroundColor = MaterialTheme.colors.primaryVariant
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Text(
                text = "Die aktuelle Playlist",
                style = MaterialTheme.typography.h1,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            )
            {
                items(playlist.recentSongs.reversed()) {
                        item: Song -> ListEntry(song = item)
                }
            }
        }
    }
}

@Composable
private fun ListEntry(song: Song) {
    Card (
        modifier = Modifier
            .fillMaxWidth(),
        elevation = 12.dp
    )
    {
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = song.title,
                style = MaterialTheme.typography.h2,
                modifier = Modifier.fillMaxWidth()
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = song.artist + " (" + song.year + ")",
                    style = MaterialTheme.typography.h3
                )
                Text(
                    text = song.getStartTime(),
                    style = MaterialTheme.typography.body1
                )
            }
        }

    }
}