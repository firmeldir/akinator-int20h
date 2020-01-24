package com.example.akinator.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController

import com.example.akinator.R
import com.example.akinator.databinding.FragmentResultBinding
import com.example.akinator.databinding.FragmentVictoryBinding


class VictoryFragment : Fragment() {

    private lateinit var binding: FragmentVictoryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentVictoryBinding.inflate(inflater, container, false)
        setupUI()
        return binding.root
    }

    private fun setupUI(){
        binding.playButton.setOnClickListener {
            findNavController().navigate(R.id.action_victoryFragment_to_gameFragment)
        }
    }


}
