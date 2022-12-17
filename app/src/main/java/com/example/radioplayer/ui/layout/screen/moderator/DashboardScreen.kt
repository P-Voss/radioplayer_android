package com.example.radioplayer.ui.layout.screen.moderator

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarOutline
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.radioplayer.entity.Feedback
import com.example.radioplayer.ui.viewModel.ModeratorViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DashboardScreen(
    moderatorViewModel: ModeratorViewModel,
    onModerationClick: () -> Unit,
    onPlaylistClick: () -> Unit,
    onRequestClick: () -> Unit
) {
    val playlistFeedback by moderatorViewModel.playlistFeedback.collectAsState()
    val moderationFeedback by moderatorViewModel.moderationFeedback.collectAsState()
    val requests by moderatorViewModel.songrequests.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp),
    )
    {
        Text(text = "Dashboard", style = MaterialTheme.typography.h1, textAlign = TextAlign.Center)

        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxHeight(0.6f)
                .fillMaxWidth(),
        ) {
            Card(
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth(),
                onClick = { onPlaylistClick() }
            ) {
                Column {
                    Text(text = "Playlist Bewertungen", style = MaterialTheme.typography.h2, textAlign = TextAlign.Center)
                    Row(
                        modifier = Modifier
                            .padding(12.dp)
                            .fillMaxWidth()
                    ) {
                        if (playlistFeedback.isNotEmpty()) {

                            Column(
                                modifier = Modifier.weight(0.2f),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(text = "Anzahl", style = MaterialTheme.typography.h3)
                                Text(
                                    text = playlistFeedback.size.toString(),
                                    style = MaterialTheme.typography.body1
                                )
                            }


                            Column(
                                modifier = Modifier.weight(0.4f),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(text = "Letzte", style = MaterialTheme.typography.h3)

                                Row {
                                    for (i in 1..5) {
                                        if (playlistFeedback.first().score >= i) {
                                            Icon(Icons.Filled.Star, contentDescription = "Star")
                                        } else {
                                            Icon(Icons.Filled.StarOutline, contentDescription = "Star")
                                        }
                                    }
                                }

//                                Row {
//                                    Text(
//                                        text = playlistFeedback.first().score.toString(),
//                                        style = MaterialTheme.typography.body1
//                                    )
//                                    Icon(Icons.Filled.Star, contentDescription = "Star")
//                                }
                            }

                            Column(
                                modifier = Modifier.weight(0.4f),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(text = "Durchschnitt", style = MaterialTheme.typography.h3)
                                Row {
                                    for (i in 1..5) {
                                        if (playlistFeedback.map { feedback: Feedback -> feedback.score }.average() >= i) {
                                            Icon(Icons.Filled.Star, contentDescription = "Star")
                                        } else {
                                            Icon(Icons.Filled.StarOutline, contentDescription = "Star")
                                        }
                                    }
                                }
//                                Text(
//                                    text = playlistFeedback.map { feedback: Feedback -> feedback.score }
//                                        .average().toString(),
//                                    style = MaterialTheme.typography.body1
//                                )
                            }
                        } else {
                            Text(
                                text = "Es gab noch kein Feedback",
                                style = MaterialTheme.typography.h3
                            )
                        }
                    }
                }
            }

            Card(
                modifier = Modifier
                    .padding(12.dp)
                    .fillMaxWidth(),
                onClick = { onModerationClick() }
            ) {
                Column {
                    Text(text = "Moderation Bewertungen", style = MaterialTheme.typography.h2, textAlign = TextAlign.Center)
                    Row(
                        modifier = Modifier
                            .padding(12.dp)
                            .fillMaxWidth()
                    ) {
                        if (moderationFeedback.isNotEmpty()) {

                            Column(modifier = Modifier.weight(0.2f)) {
                                Text(text = "Anzahl", style = MaterialTheme.typography.h3)
                                Text(
                                    text = moderationFeedback.size.toString(),
                                    style = MaterialTheme.typography.body1
                                )
                            }


                            Column(
                                modifier = Modifier.weight(0.4f),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(text = "Letzte", style = MaterialTheme.typography.h3)

                                Row {
                                    for (i in 1..5) {
                                        if (moderationFeedback.first().score >= i) {
                                            Icon(Icons.Filled.Star, contentDescription = "Star")
                                        } else {
                                            Icon(Icons.Filled.StarOutline, contentDescription = "Star")
                                        }
                                    }
                                }
                            }

                            Column(
                                modifier = Modifier.weight(0.4f),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(text = "Durchschnitt", style = MaterialTheme.typography.h3)
                                Row {
                                    for (i in 1..5) {
                                        if (moderationFeedback.map { feedback: Feedback -> feedback.score }.average() >= i) {
                                            Icon(Icons.Filled.Star, contentDescription = "Star")
                                        } else {
                                            Icon(Icons.Filled.StarOutline, contentDescription = "Star")
                                        }
                                    }
                                }
                            }
                        } else {
                            Text(
                                text = "Es gab noch kein Feedback",
                                style = MaterialTheme.typography.h3
                            )
                        }
                    }
                }
            }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                onClick = { onRequestClick() }
            ) {
                Column(
                    modifier = Modifier.padding(12.dp)
                ) {
                    Text(text = "Songwünsche", style = MaterialTheme.typography.h2)
                    Text(
                        text = requests.size.toString() + " Offene Liedwünsche",
                        style = MaterialTheme.typography.body1
                    )
                }
            }

        }
    }
}