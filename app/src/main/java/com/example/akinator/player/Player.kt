package com.example.akinator.player

import android.app.Service
import android.content.Context
import android.net.Uri
import android.util.Log
import com.example.akinator.model.SongPreview
import com.google.android.exoplayer2.ExoPlaybackException
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory
import com.google.android.exoplayer2.util.Util
import javax.inject.Inject

interface Player {
    fun play()
    fun pause()
    fun playSong(song: SongPreview)
    fun stop()
    fun release()
}

private val TAG = ExoPlayer::class.java.name

class ExoPlayer @Inject constructor(
    private val service: Service,
    private val playbackStateManager: PlaybackStateManager,
    private val audioFocusManager: AudioFocusManager
): Player, com.google.android.exoplayer2.Player.EventListener {

    private val player: SimpleExoPlayer
            = ExoPlayerFactory.newSimpleInstance(service).apply { addListener(this@ExoPlayer) }

    override fun play() {
        val focus = audioFocusManager.requestFocus()
        if(focus) {
            player.playWhenReady = true
            playbackStateManager.setPlayingState()
        }
    }

    override fun pause() {
        player.playWhenReady = false
        playbackStateManager.setPausedState()
        audioFocusManager.releaseFocus()
    }

    override fun playSong(song: SongPreview) {
        val focus = audioFocusManager.requestFocus()
        Log.d(TAG, "playTrack: $focus")
        if(focus) {
            player.playProgressiveUri(Uri.parse(song.preview), service)
            player.playWhenReady = true
            playbackStateManager.setPlayingState()
        }
    }


    override fun stop() {
        player.stop(true)
        playbackStateManager.setStoppedState()
        audioFocusManager.releaseFocus()
    }

    override fun release() {
        player.release()
        audioFocusManager.releaseFocus()
    }

    override fun onPlayerError(error: ExoPlaybackException?) {
        playbackStateManager.setErrorState()
        audioFocusManager.releaseFocus()
    }
}

fun SimpleExoPlayer.playProgressiveUri(uri: Uri, context: Context) {
    val dataSourceFactor = DefaultHttpDataSourceFactory(Util.getUserAgent(context, "akinator"))
    val mediaSource = ProgressiveMediaSource.Factory(dataSourceFactor).createMediaSource(uri)
    prepare(mediaSource)
}