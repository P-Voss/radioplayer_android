package com.example.radioplayer.ui.layout.screen.moderator

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.radioplayer.entity.SongRequest

@Composable
fun RequestScreen(
    requests: List<SongRequest>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.9f)
            .padding(12.dp)
    ) {
        Card(
            modifier = Modifier.fillMaxSize(),
            backgroundColor = MaterialTheme.colors.primaryVariant
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
            )
            {
                Text(text = "Song Requests", style = MaterialTheme.typography.h1, textAlign = TextAlign.Center)
                LazyColumn(
                    modifier = Modifier
                        .fillMaxHeight(0.7f)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                )
                {
                    items(requests) {
                            item: SongRequest -> RequestEntry(entry = item)
                    }
                }
            }
        }
    }
}

@Composable
fun RequestEntry(entry: SongRequest) {
    Card (
        modifier = Modifier
            .fillMaxWidth()
    )
    {
        Column(
            modifier = Modifier
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = entry.username, style = MaterialTheme.typography.h3)
                    Text(text = "wünscht sich")
                }
                Text(text = entry.title, style = MaterialTheme.typography.h2)
                Text(text = "Von: " + entry.artist, style = MaterialTheme.typography.h3)
            }
            if (entry.comment != "") {
                Text(text = entry.comment)
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            )
            {
                Text(
                    text = entry.getFormattedDate(),
                    style = MaterialTheme.typography.h3
                )
            }
        }
    }
}