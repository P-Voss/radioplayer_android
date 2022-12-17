package com.example.radioplayer.ui.layout.screen.user

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.radioplayer.ui.viewModel.ModeratorViewModel

@Composable
fun LoginScreen(
    moderatorViewModel: ModeratorViewModel,
    onLogin: () -> Unit
) {

    val context = LocalContext.current
    val onDashboardUpdate = { label: String -> Toast.makeText(context, label, Toast.LENGTH_SHORT) }
//    val toast = Toast.makeText(context, stringResource(R.string.task_oncreate_toast), Toast.LENGTH_SHORT)
    val focusManager = LocalFocusManager.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {

//        Image(
//            painter = painterResource(id = R.drawable.mainimage),
//            contentDescription = "Get it Done!",
//            modifier = Modifier.fillMaxWidth(),
//            contentScale = ContentScale.Fit
//        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = moderatorViewModel.loginnameInput,
            onValueChange = { moderatorViewModel.updateLoginname(it) },
            singleLine = true,
            label = {
                Text(text = "Username")
            },
            isError = false,
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = moderatorViewModel.passwordInput,
            onValueChange = { moderatorViewModel.updatePassword(it) },
            singleLine = true,
            label = {
                Text(text = "Passwort")
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
                        onDashboardUpdate = { label: String -> onDashboardUpdate(label) })
                }
            ),
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                moderatorViewModel.attemptLogin(
                    onLogin = onLogin,
                    onDashboardUpdate = { label: String -> onDashboardUpdate(label) })
            }
        )
        {
            Text(text = "Login")
        }
    }
}