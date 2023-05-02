package com.example.radioplayer.ui.theme.colors

import androidx.compose.material.Colors
import androidx.compose.material.darkColors
import androidx.compose.ui.graphics.Color



object DarkColors {
    val PrimaryColor = Color(0xFF191956)
    val PrimaryLightColor = Color(0xFF7d7dcb)
    val PrimaryDarkColor = Color(0xFF020218)

    val SecondaryColor = Color(0xFF060662)
    val SecondaryLightColor = Color(0xFF37a8a8)

    val PrimaryTextColor = Color(0xFFF3EDED)
    val SecondaryTextColor = Color(0xFFF3EDED)


    fun getMainPalette(): Colors {
        return darkColors(
            primary = PrimaryColor,
            primaryVariant = PrimaryLightColor.copy(alpha = 0.7f),
            secondary = SecondaryColor,
            secondaryVariant = SecondaryLightColor,
            surface = PrimaryDarkColor,
            onSurface = PrimaryTextColor,
            onPrimary = PrimaryTextColor,
            onSecondary = SecondaryTextColor,
            background = Color.LightGray
        )
    }
}