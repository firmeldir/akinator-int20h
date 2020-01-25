package com.example.akinator.player.di

import com.example.akinator.player.MusicService

internal fun MusicService.inject() = DaggerServiceComponent.factory()
    .create(this)
    .inject(this)