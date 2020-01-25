package com.example.akinator.repository

import com.example.akinator.model.Song
import com.example.akinator.network.Result

interface RecognitionRepository {

    suspend fun getSongByLyrics(lyrics : String) : Result<Song>

}