package com.example.akinator.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.akinator.R
import com.example.akinator.model.Song
import com.example.akinator.network.RecognitionApiFactory
import com.example.akinator.network.Result
import com.example.akinator.repository.DefaultRecognitionRepository
import com.example.akinator.repository.RecognitionRepository
import kotlinx.coroutines.launch
import java.io.IOException
import java.lang.Exception

class GameViewModel : ViewModel(){

    private val recognitionRepository : RecognitionRepository = DefaultRecognitionRepository(RecognitionApiFactory.recognitionApi)

    private val _supposedSong = MutableLiveData<Song>()
    val supposedSong : LiveData<Song>
        get() = _supposedSong

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage : LiveData<String>
        get() = _errorMessage

    init {
        _errorMessage.value = ""
    }

    fun getSongByLyrics(lyrics : String){
        viewModelScope.launch {
            when(val result = recognitionRepository.getSongByLyrics(lyrics)) {
                is Result.Success ->{
                    _supposedSong.postValue(result.data)
                }
                is Result.Error -> {
                    _errorMessage.postValue(result.exception.message)
                }
            }
        }
    }
}