package com.example.radioplayer.ui.layout.screen.user

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.radioplayer.R
import com.example.radioplayer.ui.layout.components.RatingBar
import com.example.radioplayer.ui.viewModel.RadioplayerViewModel

@Composable
fun RatePlaylistScreen(radioplayerViewModel: RadioplayerViewModel) {
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current

    var playlistScore by remember { mutableStateOf(4) }
    var comment by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    val toast = Toast.makeText(context, stringResource(R.string.toast_feedback_saved), Toast.LENGTH_SHORT)

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
            Text(
                text = stringResource(R.string.feedback_headline),
                style = MaterialTheme.typography.h1,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Card(elevation = 12.dp)
            {
                Column(
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.padding(12.dp)
                        .fillMaxWidth()
                )
                {
                    Text(
                        text = stringResource(R.string.feedback_label_playlist),
                        style = MaterialTheme.typography.h2,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                    RatingBar(
                        score = playlistScore,
                        onClick = { score: Int -> playlistScore = score }
                    )

                    OutlinedTextField(
                        value = username,
                        onValueChange = { username = it },
                        label = {
                            Text(text = stringResource(R.string.feedback_label_username), style = MaterialTheme.typography.body1, color = Color.Black)
                        },
                        keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(
                            onNext = { focusManager.moveFocus(FocusDirection.Down) }
                        ),
                        modifier = Modifier.background(Color.White, RoundedCornerShape(5.dp))
                            .fillMaxWidth(),
                        colors = TextFieldDefaults.textFieldColors(textColor = Color.Black)
                    )

                    OutlinedTextField(
                        value = comment,
                        onValueChange = { comment = it },
                        label = {
                            Text(text = stringResource(R.string.feedback_label_comment), style = MaterialTheme.typography.body1, color = Color.Black)
                        },
                        singleLine = false,
                        maxLines = 10,
                        isError = false,
                        keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = {
                                radioplayerViewModel.sendPlaylistFeedback(
                                    score = playlistScore,
                                    comment = comment,
                                    username = username,
                                    onCompleted = {
                                        focusManager.clearFocus()
                                        toast.show()
                                        comment = ""
                                        username = ""
                                        focusManager.clearFocus()
                                    }
                                )
                            }
                        ),
                        modifier = Modifier.background(Color.White, RoundedCornerShape(5.dp))
                            .fillMaxWidth(),
                        colors = TextFieldDefaults.textFieldColors(textColor = Color.Black)
                    )

                    Button(
                        onClick = {
                            radioplayerViewModel.sendPlaylistFeedback(
                                score = playlistScore,
                                comment = comment,
                                username = username,
                                onCompleted = {
                                    focusManager.clearFocus()
                                    toast.show()
                                    comment = ""
                                    username = ""
                                }
                            )
                        },
                        modifier = Modifier.fillMaxWidth()
                    )
                    {
                        Text(text = stringResource(R.string.feedback_label_submit), style = MaterialTheme.typography.body2)
                    }
                }
            }
        }
    }
}