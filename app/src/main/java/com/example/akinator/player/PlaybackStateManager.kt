package com.example.akinator.player

import android.support.v4.media.session.MediaSessionCompat
import android.support.v4.media.session.PlaybackStateCompat
import javax.inject.Inject

private const val PLAYBACK_SPEED = 1.0f
private const val DEFAULT_POSITION = 0L

class PlaybackStateManager @Inject constructor(private val mediaSession: MediaSessionCompat){

    private val builder
        get() = PlaybackStateCompat.Builder()

    fun setPlayingState() {
        val state = builder.run {
            setState(PlaybackStateCompat.STATE_PLAYING, DEFAULT_POSITION, PLAYBACK_SPEED)
            build()
        }
        mediaSession.setPlaybackState(state)
    }

    fun setPausedState() {
        val state = builder.run {
            setState(PlaybackStateCompat.STATE_PAUSED, DEFAULT_POSITION, PLAYBACK_SPEED)
            build()
        }
        mediaSession.setPlaybackState(state)
    }

    fun setStoppedState() {
        val state = builder.run {
            setState(PlaybackStateCompat.STATE_STOPPED, DEFAULT_POSITION, PLAYBACK_SPEED)
            build()
        }
        mediaSession.setPlaybackState(state)
    }

    fun setErrorState() {
        val state = builder.run {
            setState(PlaybackStateCompat.STATE_ERROR, DEFAULT_POSITION, PLAYBACK_SPEED)
            build()
        }
        mediaSession.setPlaybackState(state)
    }
}