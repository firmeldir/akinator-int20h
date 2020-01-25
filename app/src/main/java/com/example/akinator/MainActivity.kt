package com.example.akinator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.akinator.databinding.ActivityMainBinding
import com.example.akinator.player.MusicService

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        //playerViewModel

//        playerViewModel.playbackState.observe(this, Observer {
//            when(it.state) {
//                STATE_PLAYING -> Log.d(TAG, "on Create: State Playing")
//                STATE_PAUSED -> Log.d(TAG, "onCreate: STATE_PAUSED")
//                STATE_STOPPED -> Log.d(TAG, "onCreate: State_STOPPED")
//                STATE_ERROR -> Log.d(TAG, "onCreate: State_Error")
//            }
//        })
    }

    override fun onDestroy() {
        if(isFinishing) stopService(Intent(this, MusicService::class.java))
        super.onDestroy()
    }
}
