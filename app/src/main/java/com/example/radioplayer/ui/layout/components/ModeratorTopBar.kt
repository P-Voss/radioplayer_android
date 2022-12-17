package com.example.radioplayer.ui.layout.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.Logout
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun ModeratorTopBar(
    onDashboardClick: () -> Unit,
    onLogoutClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .background(color = MaterialTheme.colors.secondaryVariant)
            .padding(12.dp)
            .fillMaxWidth()
            .fillMaxHeight(0.07f),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(onClick = { onDashboardClick() }) {
            Icon(Icons.Filled.Dashboard, contentDescription = "Dashboard")
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(onClick = { onLogoutClick() }) {
            Icon(Icons.Filled.Logout, contentDescription = "Logout")
        }
    }
}