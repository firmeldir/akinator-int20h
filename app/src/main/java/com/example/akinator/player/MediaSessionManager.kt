package com.example.akinator.player

import android.content.ComponentName
import android.content.Context
import android.support.v4.media.MediaBrowserCompat
import android.support.v4.media.session.MediaControllerCompat
import android.support.v4.media.session.PlaybackStateCompat
import androidx.core.os.bundleOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.akinator.model.SongPreview

class MediaSessionManager constructor(
    private val context: Context
) {

    private val _playbackState = MutableLiveData<PlaybackStateCompat>()
    val playbackState: LiveData<PlaybackStateCompat> = _playbackState

    private val mediaBrowserConnectionCallback = MediaBrowserConnectionCallback(context)
    private val mediaBrowser = MediaBrowserCompat(
        context,
        ComponentName(context, MusicService::class.java.name),
        mediaBrowserConnectionCallback,
        null
    )

    private val mediaControllerCallback = MediaControllerCallback()
    private var mediaController: MediaControllerCompat? = null
    private val transportControls: MediaControllerCompat.TransportControls?
        get() = mediaController?.transportControls

    fun connect() {
        try { if (!mediaBrowser.isConnected) mediaBrowser.connect() } catch (e: Exception) { }
    }

    fun playSong(song: SongPreview) = transportControls?.playFromMediaId(song.preview, bundleOf(
        SONG to song)
    )
    fun play() = transportControls?.play()
    fun pause() = transportControls?.pause()
    fun stop() = transportControls?.stop()

    fun disconnect() {
        mediaController?.unregisterCallback(mediaControllerCallback)
        mediaBrowser.disconnect()
    }

    private inner class MediaBrowserConnectionCallback(private val context: Context) :
        MediaBrowserCompat.ConnectionCallback() {

        override fun onConnected() {
            mediaController = MediaControllerCompat(context, mediaBrowser.sessionToken).apply {
                registerCallback(mediaControllerCallback)
            }
        }
    }

    private inner class MediaControllerCallback : MediaControllerCompat.Callback() {
        override fun onPlaybackStateChanged(state: PlaybackStateCompat?) = _playbackState.postValue(state)
    }

}