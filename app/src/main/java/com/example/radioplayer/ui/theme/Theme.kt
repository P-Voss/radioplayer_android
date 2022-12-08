package com.example.radioplayer.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import com.example.radioplayer.ui.theme.colors.BlueColors
import com.example.radioplayer.ui.theme.colors.GreenColors


@Composable
fun RadioplayerTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = BlueColors.getMainPalette()

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}