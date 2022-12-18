package com.example.radioplayer.ui.layout.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Login
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.radioplayer.R


@Composable
fun UserTopBar(
    onHomeClick: () -> Unit,
    onLoginClick: () -> Unit,
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
                Button(onClick = { onLoginClick() }) {
                    Icon(Icons.Filled.Login, contentDescription = "Login")
                }
            }
        },
        navigationIcon = {
            Button(onClick = { onHomeClick() }) {
                Icon(Icons.Filled.Home, contentDescription = "Home")
            }
        },
        elevation = 12.dp,
        backgroundColor = MaterialTheme.colors.secondaryVariant
    )
}