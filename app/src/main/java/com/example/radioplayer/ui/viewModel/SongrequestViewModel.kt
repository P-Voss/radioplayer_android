package com.example.radioplayer.ui.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.radioplayer.entity.SongRequest
import com.example.radioplayer.network.RadioplayerApi
import kotlinx.coroutines.launch
import retrofit2.await

private const val TAG = "SongrequestViewModel"

class SongrequestViewModel: ViewModel() {

    var usernameInput by mutableStateOf("")
        private set

    var songnameInput by mutableStateOf("")
        private set

    var artistInput by mutableStateOf("")
        private set

    var commentInput by mutableStateOf("")
        private set

    fun updateUsername(value: String) {
        usernameInput = value
    }

    fun updateSongname(value: String) {
        songnameInput = value
    }

    fun updateArtist(value: String) {
        artistInput = value
    }

    fun updateComment(value: String) {
        commentInput = value
    }

    fun attemptSave(onSave: () -> Unit, onError: () -> Unit) {
        if (artistInput == "" && songnameInput == "") {
            onError()
            return
        }
        viewModelScope.launch {
            val payload = SongRequest(
                title = songnameInput,
                artist = artistInput,
                comment = commentInput,
                username = usernameInput
            )

            val result = RadioplayerApi.retrofitService.postSongrequest(payload).await()
            if (result.success) {
                onSave()
                usernameInput = ""
                songnameInput = ""
                artistInput = ""
                commentInput = ""
            }
        }
    }

}