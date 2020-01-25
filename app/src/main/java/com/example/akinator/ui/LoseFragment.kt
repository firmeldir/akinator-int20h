package com.example.akinator.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.fragment.findNavController

import com.example.akinator.R
import com.example.akinator.databinding.FragmentHomeBinding
import com.example.akinator.databinding.FragmentLoseBinding
import com.example.akinator.viewmodel.GameViewModel


class LoseFragment : Fragment() {

    private lateinit var binding: FragmentLoseBinding
    private lateinit var gameViewModel: GameViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoseBinding.inflate(inflater, container, false)
        gameViewModel = ViewModelProvider(activity as ViewModelStoreOwner).get(GameViewModel::class.java)

        setupUI()

        return binding.root
    }

    private fun setupUI(){
        binding.againButton.setOnClickListener {
            findNavController().navigate(R.id.action_loseFragment_to_gameFragment)
        }

        binding.playButton.setOnClickListener {
            gameViewModel.clearGameState()
            findNavController().navigate(R.id.action_loseFragment_to_gameFragment)
        }

        if(gameViewModel.healthCount == 0){
            binding.againButton.isGone = true
            binding.questionText.text = getString(R.string.cry)
        }
    }


}
