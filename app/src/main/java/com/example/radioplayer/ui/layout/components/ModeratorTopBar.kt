package com.example.radioplayer.ui.layout.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.Logout
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.radioplayer.R


@Composable
fun ModeratorTopBar(
    onDashboardClick: () -> Unit,
    onLogoutClick: () -> Unit,
) {
    TopAppBar(
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(R.string.headline),
                    style = MaterialTheme.typography.h1,
                )
                Button(onClick = { onLogoutClick() }) {
                    Icon(Icons.Filled.Logout, contentDescription = "Logout")
                }
            }
        },
        navigationIcon = {
            Button(onClick = { onDashboardClick() }) {
                Icon(Icons.Filled.Dashboard, contentDescription = "Dashboard")
            }
        },
        elevation = 12.dp,
        backgroundColor = MaterialTheme.colors.secondaryVariant
    )
}