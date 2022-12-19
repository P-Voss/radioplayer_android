package com.example.radioplayer.ui.layout.screen.user

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarOutline
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.radioplayer.R

@Composable
fun RateScreen() {
    var playlistScore by remember {mutableStateOf(4)}
    var ModerationScore by remember {mutableStateOf(1)}

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
            verticalArrangement = Arrangement.spacedBy(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            Text(
                text = "Sag uns deine Meinung!",
                style = MaterialTheme.typography.h1,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Card(elevation = 12.dp)
            {
                Column(
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.padding(12.dp)
                )
                {
                    Text(
                        text = "Playlist bewerten",
                        style = MaterialTheme.typography.h2,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                    RatingBar(
                        score = playlistScore,
                        onClick = { score: Int -> playlistScore = score }
                    )
//                    Button(onClick = { /*TODO*/ }) {
//                        Text(text = "Bewertung speichern", style = MaterialTheme.typography.body2)
//                    }
                }
            }
        }
    }
}

/**
 * @todo Design anpassen
 */
@Composable
private fun RatingBar(
    score: Int,
    onClick: (Int) -> Unit
)
{
    val text: String = when(score) {
        1 -> "Mies!"
        2 -> "Schlecht."
        3 -> "Ganz ok..."
        4 -> "Sehr gut!"
        else -> "Super!"
    }
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(end = 24.dp)
    )
    {
        Row {
            for (i in 1..5) {
                RatingElement(isActive = i <= score) {
                    onClick(i)
                }
            }
        }
        Text(
            text = text,
            style = MaterialTheme.typography.body1,
            modifier = Modifier.fillMaxWidth()
                .padding(0.dp),
            textAlign = TextAlign.End
        )


        OutlinedTextField(
            value = "",
            onValueChange = {  },
            label = {
                Text(text = "Kommentar (Optional)", style = MaterialTheme.typography.body1, color = Color.Black)
            },
            singleLine = false,
            maxLines = 10,
            isError = false,
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {  }
            ),
            modifier = Modifier.background(Color.White, RoundedCornerShape(5.dp))
        )

        Button(onClick = { /*TODO*/ }) {
            Text(text = "Bewertung speichern", style = MaterialTheme.typography.body2)
        }
    }
}

@Composable
private fun RatingElement(
    isActive: Boolean = false,
    onClick: () -> Unit
)
{
    if (isActive) {
        IconButton(onClick = { onClick() }) {
            Icon(
                Icons.Filled.Star,
                contentDescription = "Star"
            )
        }
    } else {
        IconButton(onClick = { onClick() }) {
            Icon(
                Icons.Filled.StarOutline,
                contentDescription = "Star"
            )
        }
    }
}
