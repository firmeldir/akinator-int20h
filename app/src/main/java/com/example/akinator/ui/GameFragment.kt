package com.example.akinator.ui


import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isGone
import androidx.lifecycle.*
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
        viewModel = ViewModelProvider(activity as ViewModelStoreOwner).get(GameViewModel::class.java)

        setupUI()

        setupObservers()

        return binding.root
    }

    private fun setupUI(){
        binding.searchButton.setOnClickListener {
            searchSongByLyrics()
        }

        binding.healthCount.text = viewModel.healthCount.toString()
    }

    private fun setupObservers(){
        viewModel.supposedSong.observe(this as LifecycleOwner, Observer {
            if(it == null)return@Observer
            findNavController().navigate(R.id.action_gameFragment_to_resultFragment)
        })

        viewModel.errorMessage.observe(this as LifecycleOwner, Observer {
            switchLoadState(false)
            if(it.isNullOrEmpty())return@Observer
            Toast.makeText(context!!, it, Toast.LENGTH_LONG).show()
        })
    }

    private fun searchSongByLyrics(){
        switchLoadState(true)
        viewModel.getSongByLyrics(binding.textInput.text.toString())
    }

    private fun switchLoadState(state: Boolean){
        binding.healthCount.isGone = state
        binding.healthImage .isGone = state
        binding.heroImage .isGone = state
        binding.searchButton .isGone = state
        binding.songLineText .isGone = state
        binding.textInput .isGone = state
        binding.textView .isGone = state

        binding.progressBar.isGone = !state
    }


}
