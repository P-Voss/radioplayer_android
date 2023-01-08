package com.example.radioplayer

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


    private val radioplayerViewModel: RadioplayerViewModel by viewModels()
    private val moderatorViewModel: ModeratorViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        radioplayerViewModel.initiateRadioplayer()

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
        radioplayerViewModel.stopTimer()
    }

    override fun onStop() {
        super.onStop()
        radioplayerViewModel.stopMediaplayer()
        radioplayerViewModel.stopTimer()
    }

    override fun onDestroy() {
        super.onDestroy()
        radioplayerViewModel.stopMediaplayer()
        radioplayerViewModel.stopTimer()
    }

    override fun onRestart() {
        super.onRestart()
        radioplayerViewModel.stopTimer()
        radioplayerViewModel.initiateRadioplayer()

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

    override fun onResume() {
        super.onResume()
        radioplayerViewModel.stopTimer()
        radioplayerViewModel.initiateRadioplayer()

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

}