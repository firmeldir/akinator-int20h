package com.example.akinator.player

import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import android.support.v4.media.MediaBrowserCompat
import android.support.v4.media.session.MediaSessionCompat
import androidx.media.MediaBrowserServiceCompat
import androidx.media.session.MediaButtonReceiver
import com.example.akinator.player.di.inject
import javax.inject.Inject

private const val EMPTY_ROOT = "emptyRoot"

class MusicService : MediaBrowserServiceCompat() {

    @Inject
    lateinit var mediaSession: MediaSessionCompat

    @Inject
    lateinit var callback: MediaSessionCallback

    override fun onCreate() {
        inject()
        super.onCreate()

        mediaSession.apply {
            setMediaButtonReceiver(buildMediaButtonReceiverPendingIntent())
            setCallback(callback)
            isActive = true
        }

        sessionToken = mediaSession.sessionToken
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        MediaButtonReceiver.handleIntent(mediaSession, intent)
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onGetRoot(
        clientPackageName: String,
        clientUid: Int,
        rootHints: Bundle?
    ): BrowserRoot? {
        return BrowserRoot(EMPTY_ROOT, null)
    }

    override fun onLoadChildren(
        parentId: String,
        result: Result<MutableList<MediaBrowserCompat.MediaItem>>
    ) {}

    override fun onDestroy() {
        mediaSession.run {
            isActive = false
            release()
        }
        callback.release()
        super.onDestroy()
    }

    private fun buildMediaButtonReceiverPendingIntent(): PendingIntent {
        val intent = Intent(Intent.ACTION_MEDIA_BUTTON, null, this, MediaButtonReceiver::class.java)
        return PendingIntent.getBroadcast(this, 0, intent, 0)
    }
}