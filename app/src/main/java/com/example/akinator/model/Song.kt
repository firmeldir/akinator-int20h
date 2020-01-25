package com.example.akinator.model

import com.example.akinator.network.ApiResponse

data class Song(
    val title : String,
    val artist : String,
    val lyrics : String
){
    companion object{
        fun getSongFromApiResponse(response: ApiResponse){

        }
    }
}