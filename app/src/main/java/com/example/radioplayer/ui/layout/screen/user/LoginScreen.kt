package com.example.radioplayer.ui.layout.screen.user

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.radioplayer.R
import com.example.radioplayer.ui.viewModel.ModeratorViewModel

@Composable
fun LoginScreen(
    moderatorViewModel: ModeratorViewModel,
    onLogin: () -> Unit
) {

    val context = LocalContext.current
    val onDashboardUpdate = { label: String ->
        val toast = Toast.makeText(context, label, Toast.LENGTH_SHORT)
        toast.show()
    }
    val focusManager = LocalFocusManager.current

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
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            TextField(
                value = moderatorViewModel.loginnameInput,
                onValueChange = { moderatorViewModel.updateLoginname(it) },
                singleLine = true,
                label = {
                    Text(text = stringResource(R.string.login_label_username), style = MaterialTheme.typography.body1, color = Color.Black)
                },
                isError = false,
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.White),
                colors = TextFieldDefaults.textFieldColors(textColor = Color.Black)
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = moderatorViewModel.passwordInput,
                onValueChange = { moderatorViewModel.updatePassword(it) },
                singleLine = true,
                label = {
                    Text(text = stringResource(R.string.login_label_password), style = MaterialTheme.typography.body1, color = Color.Black)
                },
                isError = false,
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Password
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        moderatorViewModel.attemptLogin(
                            onLogin = onLogin,
                            onDashboardUpdate = { onDashboardUpdate(it) })
                    }
                ),
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color.White),
                colors = TextFieldDefaults.textFieldColors(textColor = Color.Black)
            )
            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    moderatorViewModel.attemptLogin(
                        onLogin = onLogin,
                        onDashboardUpdate = { onDashboardUpdate(it) })
                }
            )
            {
                Text(text = stringResource(R.string.login_label_submit), style = MaterialTheme.typography.h2)
            }
        }
    }
}