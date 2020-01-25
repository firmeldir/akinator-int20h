package com.example.akinator.viewmodel

import android.support.v4.media.session.PlaybackStateCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.akinator.model.Song
import com.example.akinator.model.SongPreview
import com.example.akinator.player.MediaSessionManager
import javax.inject.Inject

class PlayerViewModel @Inject constructor(
    private val mediaSessionManager: MediaSessionManager
) : ViewModel() {

    val playbackState: LiveData<PlaybackStateCompat> = mediaSessionManager.playbackState

    init {
        mediaSessionManager.connect()
    }

    fun playSong(song: SongPreview) = mediaSessionManager.playSong(song)
    fun play() = mediaSessionManager.play()
    fun pause() = mediaSessionManager.pause()
    fun stop() = mediaSessionManager.stop()

    override fun onCleared() {
        mediaSessionManager.disconnect()
        super.onCleared()
    }
}