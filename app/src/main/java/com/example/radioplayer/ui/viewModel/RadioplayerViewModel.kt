package com.example.radioplayer.ui.viewModel

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.CountDownTimer
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.radioplayer.Enums.PlayerState
import com.example.radioplayer.entity.Feedback
import com.example.radioplayer.entity.Playlist
import com.example.radioplayer.entity.Song
import com.example.radioplayer.network.RadioplayerApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.await
import java.util.Date

private const val TAG = "RadioplayerViewModel"

class RadioplayerViewModel: ViewModel() {

    private var mediaPlayer: MediaPlayer = MediaPlayer()

    private var timer: CountDownTimer? = null

    private var timerIsActive = false

    private val _currentSong = MutableStateFlow(Song())
    val currentSong: StateFlow<Song> = _currentSong.asStateFlow()

    private val _playlist = MutableStateFlow(Playlist())
    val playlist: StateFlow<Playlist> = _playlist.asStateFlow()

    var playerState by mutableStateOf(PlayerState.INACTIVE)
        private set

    private var currentSongInitialTimestamp: Int = 0

    private fun startMediaplayer() {
        Log.d(TAG, "Start Mediaplayer")
        Log.d(TAG, playerState.name)
        if (playerState == PlayerState.ACTIVE) {
            return
        }

        mediaPlayer = MediaPlayer().apply {
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build()
            )
            setDataSource(currentSong.value.streamUrl)
            setVolume(1.0f, 1.0f)
            prepareAsync()
        }

        mediaPlayer.setOnPreparedListener(MediaPlayer.OnPreparedListener {
            it.start()
            it.seekTo((currentSong.value.lengthInSeconds - currentSong.value.remainingDuration) * 1000)
            playerState = PlayerState.ACTIVE
        })
    }

    fun stopMediaplayer() {
        Log.d(TAG, "Stop Mediaplayer")
        Log.d(TAG, playerState.name)
        if (playerState == PlayerState.INACTIVE) {
            return
        }
        if(mediaPlayer.isPlaying) {
            mediaPlayer.stop()
            mediaPlayer.release()
        }
        playerState = PlayerState.INACTIVE
    }

    fun stopTimer() {
        if (timerIsActive) {
            timer?.cancel()
            timerIsActive = false
        }
    }

    fun toggleMediaplayer() {
        Log.d(TAG, "Toggle Mediaplayer")
        Log.d(TAG, playerState.name)
        if (playerState == PlayerState.INACTIVE) {
            startMediaplayer()
        } else {
            stopMediaplayer()
        }
    }


    fun initiateRadioplayer() {
        stopMediaplayer()
        stopTimer()
        updateCurrentInformation{loadCurrentInformation()}
    }

    private fun loadCurrentInformation() {
        val duration = currentSong.value.remainingDuration
        Log.d(TAG, duration.toString())
        if (timerIsActive) {
            return
        }
        viewModelScope.launch {
            timerIsActive = true

            val currentDate = Date()
            timer = object : CountDownTimer(currentSong.value.endDateTime!!.time - currentDate.time, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    val remainingDuration = currentSong.value.remainingDuration - 1
                    _currentSong.update { currentState ->
                        currentState.copy(remainingDuration = remainingDuration)
                    }
                }

                override fun onFinish() {
                    Log.d(TAG, "About to call for an Update")

                    updateCurrentInformation {
                        if (playerState == PlayerState.ACTIVE) {
                            stopMediaplayer()
                            startMediaplayer()
                        }
                        loadCurrentInformation()
                    }
                }
            }
            (timer as CountDownTimer).start()
        }
    }

    private fun updateCurrentInformation(onUpdate: () -> Unit) {
        Log.d(TAG, "Calling Update")
        Log.d(TAG, "Timer is active: " + timerIsActive)
        if (timerIsActive) {
            timer?.cancel()
            timerIsActive = false
        }
        viewModelScope.launch {
            val result = RadioplayerApi.retrofitService.getCurrentInformation().await()
            if (result.success) {
                _playlist.value = result.playlist
                _currentSong.value = result.currentSong

                currentSongInitialTimestamp = result.currentSong.lengthInSeconds - result.currentSong.remainingDuration

                onUpdate()
            }
        }
    }

    fun sendPlaylistFeedback(score: Int, comment: String = "", username: String = "", onCompleted: () -> Unit) {
        val payload = Feedback(score = score, comment = comment, username = normalizeUsername(username))
        viewModelScope.launch {
            val result = RadioplayerApi.retrofitService.postPlaylistFeedback(payload).await()
            if (result.success) {
                onCompleted()
            }
        }
    }

    fun sendModerationFeedback(score: Int, comment: String = "", username: String = "", onCompleted: () -> Unit) {
        val payload = Feedback(score = score, comment = comment, username = normalizeUsername(username))
        viewModelScope.launch {
            val result = RadioplayerApi.retrofitService.postModerationFeedback(payload).await()
            if (result.success) {
                onCompleted()
            }
        }
    }

    private fun normalizeUsername(username: String = ""): String {
        return if (username == "") {
            "anonym"
        } else {
            username
        }
    }

}