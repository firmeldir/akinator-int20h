package com.example.akinator.network

import com.example.akinator.model.Song

data class ApiResponse(
    val result : List<SongResult>
)

data class SongResult(
    val title : String,
    val artist : String,
    val lyrics : String
){
    fun toSong() : Song = Song(title, artist, lyrics)
}