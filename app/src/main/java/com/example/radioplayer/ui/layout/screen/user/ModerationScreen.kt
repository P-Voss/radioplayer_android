package com.example.radioplayer.ui.layout.screen.user

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.StarRate
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.radioplayer.entity.User


@Composable
fun ModerationScreen(moderator: User, onRate: () -> Unit) {
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
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Text(
                text = moderator.fullname,
                style = MaterialTheme.typography.h1,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            IconButton(
                onClick = { onRate() },
                Modifier
                    .background(color = MaterialTheme.colors.surface)
                    .fillMaxWidth()
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                ) {
                    Text(text = "Dein Feedback!")
                    Row {
                        Icon(imageVector = Icons.Filled.StarRate, contentDescription = "Bewertung")
                        Icon(imageVector = Icons.Filled.StarRate, contentDescription = "Bewertung")
                        Icon(imageVector = Icons.Filled.StarRate, contentDescription = "Bewertung")
                        Icon(imageVector = Icons.Filled.StarRate, contentDescription = "Bewertung")
                        Icon(imageVector = Icons.Filled.StarRate, contentDescription = "Bewertung")
                    }
                }
            }

            Card {
                Text(text = "Mein Motto: " + moderator.motto, modifier = Modifier.padding(12.dp))
            }

            Card {
                Text(text = moderator.info, modifier = Modifier.padding(12.dp))
            }

            Card {
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Social Media", style = MaterialTheme.typography.h2)

                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = "Twitter", style = MaterialTheme.typography.body2)
                        Text(text = moderator.twitterHandle, style = MaterialTheme.typography.body2)
                    }
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = "Instagram", style = MaterialTheme.typography.body2)
                        Text(text = moderator.instagram, style = MaterialTheme.typography.body2)
                    }

                }
            }
        }
    }
}
