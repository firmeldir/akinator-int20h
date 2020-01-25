package com.example.akinator.ui


import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.akinator.BuildConfig

import com.example.akinator.R
import com.example.akinator.databinding.FragmentGameBinding
import com.example.akinator.databinding.FragmentHomeBinding
import com.example.akinator.viewmodel.GameViewModel


class GameFragment : Fragment() {

    private lateinit var binding: FragmentGameBinding
    private lateinit var viewModel: GameViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGameBinding.inflate(inflater, container, false)
        viewModel = ViewModelProviders.of(this).get(GameViewModel::class.java) //todo make normalisation

        setupUI()

        setupObservers()

        return binding.root
    }

    private fun setupUI(){
        binding.searchButton.setOnClickListener {
            //findNavController().navigate(R.id.action_gameFragment_to_resultFragment)

            viewModel.getSongByLyrics(binding.textInput.text.toString())
        }
    }

    private fun setupObservers(){
        viewModel.supposedSong.observe(this as LifecycleOwner, Observer {
            Toast.makeText(context!!, "RESULT: ${it.title} + ${it.artist}", Toast.LENGTH_LONG).show()
        })

        viewModel.errorMessage.observe(this as LifecycleOwner, Observer {
            if(it.isNullOrEmpty())return@Observer
            Toast.makeText(context!!, it, Toast.LENGTH_LONG).show()
        })
    }


}
