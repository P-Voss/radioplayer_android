package com.example.radioplayer.ui.layout.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Login
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun UserTopBar(
    onHomeClick: () -> Unit,
    onLoginClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .background(color = MaterialTheme.colors.secondaryVariant)
            .padding(12.dp)
            .fillMaxWidth()
            .fillMaxHeight(0.07f),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(onClick = { onHomeClick() }) {
            Icon(Icons.Filled.Home, contentDescription = "Home")
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(onClick = { onLoginClick() }) {
            Icon(Icons.Filled.Login, contentDescription = "Login")
        }
    }
}