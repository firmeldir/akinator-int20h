package com.example.akinator.player

import android.app.Service
import android.content.Context
import android.media.AudioManager
import android.support.v4.media.session.MediaSessionCompat
import androidx.media.AudioAttributesCompat
import androidx.media.AudioFocusRequestCompat
import androidx.media.AudioManagerCompat
import javax.inject.Inject

class AudioFocusManager @Inject constructor(
    private val service: Service,
    private val mediaSession: MediaSessionCompat
) : AudioManager.OnAudioFocusChangeListener {

    private val audioManager = service.getSystemService(Context.AUDIO_SERVICE) as AudioManager

    private val focusRequest = AudioFocusRequestCompat.Builder(AudioManagerCompat.AUDIOFOCUS_GAIN).run {
        setOnAudioFocusChangeListener(this@AudioFocusManager)
        setWillPauseWhenDucked(false)
        setAudioAttributes(
            AudioAttributesCompat.Builder().run {
                setUsage(AudioAttributesCompat.USAGE_MEDIA)
                setContentType(AudioAttributesCompat.CONTENT_TYPE_MUSIC)
                build()
            }
        )
        build()
    }

    override fun onAudioFocusChange(focus: Int) {
        when (focus) {
            AudioManager.AUDIOFOCUS_GAIN -> mediaSession.controller.transportControls.play()
            AudioManager.AUDIOFOCUS_LOSS -> mediaSession.controller.transportControls.pause()
        }
    }

    fun requestFocus(): Boolean {
        val focus = AudioManagerCompat.requestAudioFocus(audioManager, focusRequest)
        return focus == AudioManager.AUDIOFOCUS_REQUEST_GRANTED
    }

    fun releaseFocus() = AudioManagerCompat.abandonAudioFocusRequest(audioManager, focusRequest)

}