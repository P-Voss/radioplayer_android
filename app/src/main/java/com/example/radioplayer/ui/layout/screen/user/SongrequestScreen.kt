package com.example.radioplayer.ui.layout.screen.user

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.unit.dp
import com.example.radioplayer.R
import com.example.radioplayer.ui.viewModel.SongrequestViewModel

@Composable
fun SongrequestScreen(songrequestViewModel: SongrequestViewModel = SongrequestViewModel()) {
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current
    val successToast = Toast.makeText(context, "Dein Wunsch wurde eingetragen!", Toast.LENGTH_SHORT)
    val errorToast = Toast.makeText(context, "Trag bitte einen Künstler oder einen Liednamen ein", Toast.LENGTH_SHORT)

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
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            OutlinedTextField(
                value = songrequestViewModel.usernameInput,
                onValueChange = { songrequestViewModel.updateUsername(it) },
                singleLine = true,
                label = {
                    Text(text = stringResource(R.string.request_label_username), style = MaterialTheme.typography.body1, color = Color.Black)
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
                    .background(Color.White, RoundedCornerShape(5.dp)),
            )

            OutlinedTextField(
                value = songrequestViewModel.songnameInput,
                onValueChange = { songrequestViewModel.updateSongname(it) },
                singleLine = true,
                label = {
                    Text(text = stringResource(R.string.request_label_songname), style = MaterialTheme.typography.body1, color = Color.Black)
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
                    .background(Color.White, RoundedCornerShape(5.dp))
            )

            OutlinedTextField(
                value = songrequestViewModel.artistInput,
                onValueChange = { songrequestViewModel.updateArtist(it) },
                singleLine = true,
                label = {
                    Text(text = stringResource(R.string.request_label_artist), style = MaterialTheme.typography.body1, color = Color.Black)
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
                    .background(Color.White, RoundedCornerShape(5.dp))
            )

            OutlinedTextField(
                value = songrequestViewModel.commentInput,
                onValueChange = { songrequestViewModel.updateComment(it) },
                label = {
                    Text(text = stringResource(R.string.request_label_comment), style = MaterialTheme.typography.body1, color = Color.Black)
                },
                singleLine = false,
                maxLines = 10,
                isError = false,
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = { songrequestViewModel.attemptSave(onSave = {successToast.show()}, onError = {errorToast.show()}) }
                ),
                modifier = Modifier
                    .fillMaxWidth().height(120.dp)
                    .background(Color.White, RoundedCornerShape(5.dp))
            )

            Button(onClick = { songrequestViewModel.attemptSave(onSave = {successToast.show()}, onError = {errorToast.show()}) }) {
                Text(text = stringResource(R.string.request_label_submit), style = MaterialTheme.typography.h2)
            }
        }
    }

}