package com.example.akinator.player.di

import android.app.Service
import android.support.v4.media.session.MediaSessionCompat
import com.example.akinator.player.ExoPlayer
import com.example.akinator.player.MusicService
import com.example.akinator.player.Player
import dagger.Module
import dagger.Provides

@Module
object MusicServiceModule  {

    @Provides
    @ServiceScope
    fun provideMediaSession(service: Service): MediaSessionCompat
            = MediaSessionCompat(service, MusicService::class.java.name)

    @Provides
    fun providePlayer(exoPlayer: ExoPlayer) : Player = exoPlayer
}