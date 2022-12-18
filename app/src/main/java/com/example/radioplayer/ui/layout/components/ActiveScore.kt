package com.example.radioplayer.ui.layout.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarOutline
import androidx.compose.runtime.Composable


@Composable
fun ActiveScore(score: Float) {
    Row {
        for (i in 1..5) {
            if (score >= i) {
                Icon(Icons.Filled.Star, contentDescription = "Star")
            } else {
                Icon(
                    Icons.Filled.StarOutline,
                    contentDescription = "Star"
                )
            }
        }
    }
}