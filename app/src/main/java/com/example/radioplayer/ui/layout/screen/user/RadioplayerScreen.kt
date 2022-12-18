package com.example.radioplayer.ui.layout.screen.user

import android.text.format.DateUtils
import android.util.Log
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.radioplayer.R
import com.example.radioplayer.ui.viewModel.RadioplayerViewModel
import kotlin.math.roundToInt

@Composable
fun RadioplayerScreen(
    radioplayerViewModel: RadioplayerViewModel
) {
    val playerState = radioplayerViewModel.playerState
    val currentSong by radioplayerViewModel.currentSong.collectAsState()
    val playlist by radioplayerViewModel.playlist.collectAsState()
    var progress by remember {
        mutableStateOf(0f)
    }

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
                modifier = Modifier.fillMaxSize()
                    .padding(24.dp)
            )
            {
                Text(text = "Aktuelle Playlist: " + playlist.theme, style = MaterialTheme.typography.h2)

                Spacer(modifier = Modifier.height(40.dp))

                Image(painter = rememberAsyncImagePainter(currentSong.imageUrl),
                    contentDescription = "Logo",
                    modifier = Modifier.fillMaxWidth(),
                    contentScale = ContentScale.Fit
                )
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = currentSong.title + " (" + currentSong.year + ")",
                    style = MaterialTheme.typography.body1
                )
                Text(
                    text = "von " + currentSong.artist,
                    style = MaterialTheme.typography.body1
                )

                if (currentSong.remainingDuration > 0) {
                    val currentPosition = (currentSong.lengthInSeconds - currentSong.remainingDuration).toFloat()
                    progress = currentPosition / currentSong.lengthInSeconds

                    LinearProgressBar(progress = progress)
                    Text(
                        text = secondsToTime(currentPosition) + " - " + secondsToTime(currentSong.lengthInSeconds.toFloat()),
                        style = MaterialTheme.typography.body2,
                        textAlign = TextAlign.End,
                        modifier = Modifier.fillMaxWidth()
                    )
                } else {
                    LinearProgressBar(progress = 0f)
                    Text(
                        text = secondsToTime(0f) + " - " + secondsToTime(0f),
                        style = MaterialTheme.typography.body2,
                        textAlign = TextAlign.End,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}

private fun secondsToTime(seconds: Float): String {
    return DateUtils.formatElapsedTime(seconds.toLong())
}

@Composable
fun LinearProgressBar(
    progress: Float
) {
    val animatedProgress = animateFloatAsState(
        targetValue = progress,
        animationSpec = tween(durationMillis = 1000, easing = LinearEasing)
    ).value

    LinearProgressIndicator(
        progress = animatedProgress,
        color = MaterialTheme.colors.secondary,
        modifier = Modifier
            .fillMaxWidth()
            .height(9.dp)
    )
}
