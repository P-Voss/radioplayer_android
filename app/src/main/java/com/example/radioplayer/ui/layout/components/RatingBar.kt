package com.example.radioplayer.ui.layout.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarOutline
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.radioplayer.R

@Composable
fun RatingBar(
    score: Int,
    onClick: (Int) -> Unit
)
{
    val text: String = when(score) {
        1 -> stringResource(R.string.feedback_one)
        2 -> stringResource(R.string.feedback_two)
        3 -> stringResource(R.string.feedback_three)
        4 -> stringResource(R.string.feedback_four)
        else -> stringResource(R.string.feedback_five)
    }
    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(end = 24.dp, bottom = 24.dp)
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