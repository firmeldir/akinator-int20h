package com.example.akinator.viewmodel

import android.app.Application
import android.support.v4.media.session.PlaybackStateCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.akinator.model.Song
import com.example.akinator.model.SongPreview
import com.example.akinator.player.MediaSessionManager
import javax.inject.Inject

class PlayerViewModel(application: Application) : AndroidViewModel(application) {

    private val mediaSessionManager: MediaSessionManager = MediaSessionManager(application.applicationContext)

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