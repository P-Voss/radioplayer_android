package com.example.radioplayer.ui.layout.screen.user

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.radioplayer.ui.viewModel.RadioplayerViewModel

@Composable
fun RadioplayerScreen(
    radioplayerViewModel: RadioplayerViewModel
) {
    val playerState = radioplayerViewModel.playerState
    val currentSong by radioplayerViewModel.currentSong.collectAsState()
    val playlist by radioplayerViewModel.playlist.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(1f)
            .padding(32.dp)
    ) {

        Text(text = "Radio Vossmeister >:]", style = MaterialTheme.typography.h1)

        Spacer(modifier = Modifier.height(24.dp))

        Text(text = "Current Song:" + currentSong.title + " from " + currentSong.artist)

        if (currentSong.remainingDuration > 0) {
            Text(text = "Restdauer " + currentSong.remainingDuration + " Sekunden")
        } else {
            Text(text = "Liedwechsel")
        }

    }

}