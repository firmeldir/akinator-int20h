package com.example.akinator.player.di

import android.app.Service
import com.example.akinator.player.MusicService
import dagger.BindsInstance
import dagger.Component

@ServiceScope
@Component(modules = [MusicServiceModule::class])
interface ServiceComponent {

    fun inject(service: MusicService)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance service: Service): ServiceComponent
    }

}