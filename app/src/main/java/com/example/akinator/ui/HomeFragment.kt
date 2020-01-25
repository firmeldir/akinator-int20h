package com.example.akinator.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.fragment.findNavController

import com.example.akinator.R
import com.example.akinator.databinding.FragmentHomeBinding
import com.example.akinator.viewmodel.GameViewModel

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var gameViewModel: GameViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        gameViewModel = ViewModelProvider(activity as ViewModelStoreOwner).get(GameViewModel::class.java)

        setupUI()

        gameViewModel.clearGameState()

        return binding.root
    }

    private fun setupUI(){
        binding.playButton.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_gameFragment)
        }
    }

}
