package com.example.akinator.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController

import com.example.akinator.R
import com.example.akinator.databinding.FragmentHomeBinding
import com.example.akinator.databinding.FragmentLoseBinding


class LoseFragment : Fragment() {

    private lateinit var binding: FragmentLoseBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoseBinding.inflate(inflater, container, false)
        setupUI()
        return binding.root
    }

    private fun setupUI(){
        binding.againButton.setOnClickListener {
            findNavController().navigate(R.id.action_loseFragment_to_gameFragment)
        }

        binding.playButton.setOnClickListener {
            findNavController().navigate(R.id.action_loseFragment_to_gameFragment)
        }
    }


}
