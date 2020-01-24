package com.example.akinator.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController

import com.example.akinator.R
import com.example.akinator.databinding.FragmentLoseBinding
import com.example.akinator.databinding.FragmentResultBinding


class ResultFragment : Fragment() {

    private lateinit var binding: FragmentResultBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentResultBinding.inflate(inflater, container, false)
        setupUI()
        return binding.root
    }

    private fun setupUI(){
        binding.loseButton.setOnClickListener {
            findNavController().navigate(R.id.action_resultFragment_to_loseFragment)
        }

        binding.victoryButton.setOnClickListener {
            findNavController().navigate(R.id.action_resultFragment_to_victoryFragment)
        }
    }

}
