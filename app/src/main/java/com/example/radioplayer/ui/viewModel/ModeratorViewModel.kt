package com.example.radioplayer.ui.viewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.radioplayer.Enums.LoginState
import com.example.radioplayer.entity.Feedback
import com.example.radioplayer.entity.SongRequest
import com.example.radioplayer.entity.User
import com.example.radioplayer.network.RadioplayerApi
import com.example.radioplayer.network.request.LoginRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.await
import java.util.Date

class ModeratorViewModel: ViewModel() {

    private val _user = MutableStateFlow(User())
    val user: StateFlow<User> = _user.asStateFlow()

    private val _moderationFeedback = MutableStateFlow<MutableList<Feedback>>(mutableListOf())
    val moderationFeedback: StateFlow<List<Feedback>> = _moderationFeedback.asStateFlow()

    private val _playlistFeedback = MutableStateFlow<MutableList<Feedback>>(mutableListOf())
    val playlistFeedback: StateFlow<List<Feedback>> = _playlistFeedback.asStateFlow()

    private val _songrequests = MutableStateFlow<MutableList<SongRequest>>(mutableListOf())
    val songrequests: StateFlow<List<SongRequest>> = _songrequests.asStateFlow()

    private var mostRecentModerationFeedback by mutableStateOf(Date(1))
    private var mostRecentPlaylistFeedback by mutableStateOf(Date(1))
    private var mostSongRequest by mutableStateOf(Date(1))

    var loginnameInput by mutableStateOf("")
        private set
    var passwordInput by mutableStateOf("")
        private set

    fun isLoggedIn(): Boolean {
        return user.value.loginState === LoginState.SIGNED_IN
    }

    fun updateLoginname(name: String) {
        loginnameInput = name
    }

    fun updatePassword(password: String) {
        passwordInput = password
    }


    fun attemptLogin(
        onLogin: () -> Unit,
        onDashboardUpdate: (String) -> Unit
    ) {
        if (loginnameInput == "" || passwordInput == "") {
//            return
        }

        val payload = LoginRequest(
            username = loginnameInput,
            password = passwordInput
        )
        Log.d("ModeratorViewModel", payload.toString())

        viewModelScope.launch {
            try {
                Log.d("ModeratorViewModel", "Attempting Request")
                val result = RadioplayerApi.retrofitService.login(payload).await()
                Log.d("ModeratorViewModel", "Executed Request")

                Log.d("ModeratorViewModel", result.toString())
                if (result.success) {
                    _user.update { currentState ->
                        currentState.copy(
                            name = result.user.name,
                            userId = result.user.userId,
                            loginState = LoginState.SIGNED_IN,
                            password = "",
                        )
                    }
                    onLogin()
                    loginnameInput = ""
                    passwordInput = ""
                    refreshDashboard(onDashboardUpdate)
                }
            } catch (e: Exception) {
                Log.d("ModeratorViewModel", "Caught Exception: " + e.message)
            }
        }
    }

    private fun refreshDashboard(onDashboardUpdate: (String) -> Unit) {
        viewModelScope.launch {
            try {
                Log.d("ModeratorViewModel", "Attempting Request")
                val result = RadioplayerApi.retrofitService.loadDashboard().await()
                Log.d("ModeratorViewModel", "Executed Request")

                Log.d("ModeratorViewModel", result.toString())
                if (result.success) {

                    if (result.playlistFeedback.isNotEmpty()) {
                        val playlistFeedback = result.playlistFeedback
                        if (playlistFeedback.last().datetime.time > mostRecentPlaylistFeedback.time) {
                            var newFeedback: MutableList<Feedback> = mutableListOf()
                            newFeedback.addAll(0, playlistFeedback)
                            _playlistFeedback.value = newFeedback

                            mostRecentPlaylistFeedback = playlistFeedback.last().datetime
                            onDashboardUpdate("Neues Feedback zur Playlist erhalten!")
                        }
                    }

                    if (result.moderationFeedback.isNotEmpty()) {
                        val moderationFeedback = result.moderationFeedback
                        if (moderationFeedback.last().datetime.time > mostRecentModerationFeedback.time) {
                            var newFeedback: MutableList<Feedback> = mutableListOf()
                            newFeedback.addAll(0, moderationFeedback)
                            _moderationFeedback.value = newFeedback

                            mostRecentModerationFeedback = moderationFeedback.last().datetime
                            onDashboardUpdate("Neues Feedback zur Moderation erhalten!")
                        }
                    }

                    if (result.songRequests.isNotEmpty()) {
                        val songRequests = result.songRequests
                        if (songRequests.last().datetime.time > mostSongRequest.time) {
                            val newRequests: MutableList<SongRequest> = mutableListOf()
                            newRequests.addAll(0, songRequests)
                            _songrequests.value = newRequests

                            mostSongRequest = songRequests.last().datetime
                            onDashboardUpdate("Neuer Wunsch!")
                        }
                    }
                }
            } catch (e: Exception) {
                Log.d("ModeratorViewModel", "Caught Exception: " + e.message)
            }
        }
    }

    fun logout(onLogout: () -> Unit) {
        _user.update { currentState ->
            currentState.copy(
                userId = 0,
                name = "",
                password = "",
                loginState = LoginState.SIGNED_OFF
            )
        }
        onLogout()
    }

}