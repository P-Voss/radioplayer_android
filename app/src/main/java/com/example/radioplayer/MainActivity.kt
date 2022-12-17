package com.example.radioplayer

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.example.radioplayer.ui.theme.RadioplayerTheme
import com.example.radioplayer.ui.viewModel.ModeratorViewModel
import com.example.radioplayer.ui.viewModel.RadioplayerViewModel

class MainActivity : ComponentActivity() {

    private val mediaPlayer: MediaPlayer = MediaPlayer()

    private val radioplayerViewModel: RadioplayerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        radioplayerViewModel.initiateRadioplayer()

        val moderatorViewModel: ModeratorViewModel by viewModels()

        setContent {
            RadioplayerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    RadioplayerApp(
                        moderatorViewModel = moderatorViewModel,
                        radioplayerViewModel = radioplayerViewModel
                    )
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()
        radioplayerViewModel.stopMediaplayer()
    }

    override fun onStop() {
        super.onStop()
        radioplayerViewModel.stopMediaplayer()
    }

    override fun onDestroy() {
        super.onDestroy()
        radioplayerViewModel.stopMediaplayer()
    }

    override fun onRestart() {
        super.onRestart()
    }

    override fun onResume() {
        super.onResume()
    }

}