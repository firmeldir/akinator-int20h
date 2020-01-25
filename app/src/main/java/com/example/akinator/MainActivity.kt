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
    }

    override fun onDestroy() {
        if(isFinishing) stopService(Intent(this, MusicService::class.java))
        super.onDestroy()
    }
}
