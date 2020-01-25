package com.example.akinator.model

import android.os.Parcelable
import com.example.akinator.network.ApiResponse
import kotlinx.android.parcel.Parcelize

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

@Parcelize
data class SongPreview(
    val title: String,
    val artist: String,
    val preview: String,
    val cover: String
) : Parcelable