package com.example.akinator.ui


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.*
import androidx.navigation.fragment.findNavController

import com.example.akinator.R
import com.example.akinator.databinding.FragmentLoseBinding
import com.example.akinator.databinding.FragmentResultBinding
import com.example.akinator.viewmodel.GameViewModel
import com.example.akinator.viewmodel.PlayerViewModel


class ResultFragment : Fragment() {

    private lateinit var binding: FragmentResultBinding
    private lateinit var gameViewModel: GameViewModel
    private lateinit var playerViewModel: PlayerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentResultBinding.inflate(inflater, container, false)
        gameViewModel = ViewModelProvider(activity as ViewModelStoreOwner).get(GameViewModel::class.java)
        playerViewModel = ViewModelProviders.of(this).get(PlayerViewModel::class.java)

        setupUI()

        setupObservers()

        return binding.root
    }

    private fun setupUI(){
        binding.loseButton.setOnClickListener {
            findNavController().navigate(R.id.action_resultFragment_to_loseFragment)
            gameViewModel.loseTriggered()
        }

        binding.victoryButton.setOnClickListener {
            findNavController().navigate(R.id.action_resultFragment_to_victoryFragment)
        }
    }

    private fun setupObservers(){
        gameViewModel.supposedSong.observe(this as LifecycleOwner, Observer {
            if(it == null)return@Observer
            gameViewModel.clearSongState()
            gameViewModel.getSongPreview(it)
            binding.songTitleText.text = "\"${it.artist} - ${it.title}\""
        })

        gameViewModel.songPreview.observe(this as LifecycleOwner, Observer {
            if(it == null)return@Observer
            playerViewModel.playSong(it)
        })
    }

}
