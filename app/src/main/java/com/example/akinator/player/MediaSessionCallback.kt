package com.example.akinator.player

import android.os.Bundle
import android.support.v4.media.session.MediaSessionCompat
import com.example.akinator.model.SongPreview
import javax.inject.Inject

class MediaSessionCallback @Inject constructor(
    private val player: Player
): MediaSessionCompat.Callback() {

    override fun onPlayFromMediaId(mediaId: String?, extras: Bundle?) {
        extractSong(extras)?.let {
            player.playSong(it)
        }
    }

    override fun onPlay() = player.play()
    override fun onPause() = player.pause()
    override fun onStop() = player.stop()

    fun release() = player.release()

    private fun extractSong(extras: Bundle?): SongPreview? = extras?.getParcelable(SONG)

}