package com.example.radioplayer.ui.layout

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Stop
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.radioplayer.Enums.PlayerState
import com.example.radioplayer.ui.viewModel.RadioplayerViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Layout(
    content: @Composable (PaddingValues) -> Unit,
    playerState: PlayerState,
    topBar: @Composable () -> Unit,
    bottomBar: @Composable () -> Unit,
    onTogglePlayer: () -> Unit
)
{
    Scaffold(
        topBar = topBar,
        bottomBar = bottomBar,
        content = content,
        floatingActionButton = {
            FloatingActionButton(
                content = {
                    if (playerState == PlayerState.ACTIVE) {
                        Icon(
                            Icons.Filled.Stop,
                            contentDescription = "",
                            tint = MaterialTheme.colors.secondaryVariant,
                            modifier = Modifier.background(color = MaterialTheme.colors.error.copy(alpha = 0.0f))
                        )
                    } else {
                        Icon(
                            Icons.Filled.PlayArrow,
                            contentDescription = "",
                            tint = MaterialTheme.colors.primaryVariant,
                            modifier = Modifier.background(color = MaterialTheme.colors.surface.copy(alpha = 0.0f))
                        )
                    }
                },
                onClick = { onTogglePlayer() },
            )
        },
        floatingActionButtonPosition = FabPosition.Center,
//        isFloatingActionButtonDocked = true,
    )
}