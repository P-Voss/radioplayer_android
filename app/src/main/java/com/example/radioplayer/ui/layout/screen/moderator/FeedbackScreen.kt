package com.example.radioplayer.ui.layout.screen.moderator

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.radioplayer.entity.Feedback

@Composable
fun FeedbackScreen(
    headline: String,
    feedbackList: List<Feedback> = mutableListOf()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp),
    )
    {
        Text(text = headline, style = MaterialTheme.typography.h1, textAlign = TextAlign.Center)
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        )
        {
            items(feedbackList) {
                    item: Feedback -> FeedbackEntry(entry = item)
            }
        }
    }
}

@Composable
fun FeedbackEntry(entry: Feedback) {
    Card (
        modifier = Modifier
            .fillMaxWidth()
    )
    {
        Column(
            modifier = Modifier
                .padding(8.dp)
//                .clickable { expanded = !expanded }
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row {
                    for (i in 1..5) {
                        if (entry.score >= i) {
                            Icon(Icons.Filled.Star, contentDescription = "Star")
                        } else {
                            Icon(Icons.Filled.StarOutline, contentDescription = "Star")
                        }
                    }
                }

                Text(text = "Von: " + entry.username, style = MaterialTheme.typography.h3)
            }
            Spacer(modifier = Modifier.height(8.dp))

            if (entry.comment != "") {
                Text(text = entry.comment)
                Spacer(modifier = Modifier.height(8.dp))
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