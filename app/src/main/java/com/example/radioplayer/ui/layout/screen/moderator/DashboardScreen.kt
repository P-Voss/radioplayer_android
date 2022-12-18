package com.example.radioplayer.ui.layout.screen.moderator

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.radioplayer.entity.Feedback
import com.example.radioplayer.ui.layout.components.ActiveScore
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
                .padding(12.dp)
        )
        {
            Text(
                text = "Dashboard",
                style = MaterialTheme.typography.h1,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(24.dp),
            ) {

                Card(
                    onClick = { onPlaylistClick() },
                    elevation = 12.dp
                ) {
                    Column(
                        modifier = Modifier.padding(12.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    )
                    {
                        Text(
                            text = "Playlist Bewertungen",
                            style = MaterialTheme.typography.h2,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )

                        if (playlistFeedback.isNotEmpty()) {

                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier.fillMaxWidth()
                            )
                            {
                                Text(text = "Anzahl", style = MaterialTheme.typography.h3)
                                Text(
                                    text = playlistFeedback.size.toString(),
                                    style = MaterialTheme.typography.body1
                                )
                            }

                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier.fillMaxWidth()
                            )
                            {
                                Text(text = "Letzte", style = MaterialTheme.typography.h3)
                                ActiveScore(playlistFeedback.first().score.toFloat())
                            }

                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier.fillMaxWidth()
                            )
                            {
                                val playlistAverage = playlistFeedback.map { feedback: Feedback -> feedback.score }.average()
                                Text(text = "Durchschnitt", style = MaterialTheme.typography.h3)
                                ActiveScore(playlistAverage.toFloat())
                            }
                        } else {
                            Text(
                                text = "Es gab noch kein Feedback",
                                style = MaterialTheme.typography.h3
                            )
                        }
                    }
                }

                Card(
                    onClick = { onModerationClick() },
                    elevation = 12.dp
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        modifier = Modifier.padding(12.dp)
                    )
                    {
                        Text(
                            text = "Playlist Bewertungen",
                            style = MaterialTheme.typography.h2,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )

                        if (playlistFeedback.isNotEmpty()) {

                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier.fillMaxWidth()
                            )
                            {
                                Text(text = "Anzahl", style = MaterialTheme.typography.h3)
                                Text(
                                    text = moderationFeedback.size.toString(),
                                    style = MaterialTheme.typography.body1
                                )
                            }

                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier.fillMaxWidth()
                            )
                            {
                                Text(text = "Letzte", style = MaterialTheme.typography.h3)
                                ActiveScore(moderationFeedback.first().score.toFloat())
                            }

                            Row(
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier.fillMaxWidth()
                            )
                            {
                                val moderationAverage = moderationFeedback.map { feedback: Feedback -> feedback.score }.average()
                                Text(text = "Durchschnitt", style = MaterialTheme.typography.h3)
                                ActiveScore(moderationAverage.toFloat())
                            }
                        } else {
                            Text(
                                text = "Es gab noch kein Feedback",
                                style = MaterialTheme.typography.h3
                            )
                        }
                    }
                }

                Card(
                    onClick = { onRequestClick() },
                    elevation = 12.dp
                ) {
                    Column(
                        modifier = Modifier.padding(12.dp)
                    ) {
                        Text(
                            text = "Songwünsche",
                            style = MaterialTheme.typography.h2,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Text(
                            text = requests.size.toString() + " Offene Liedwünsche",
                            style = MaterialTheme.typography.body1
                        )
                    }
                }
            }
        }

    }
}