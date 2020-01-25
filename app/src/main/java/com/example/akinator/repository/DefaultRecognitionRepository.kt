package com.example.akinator.repository

import android.util.Log
import com.example.akinator.model.Song
import com.example.akinator.network.ApiResponse
import com.example.akinator.network.RecognitionApi
import com.example.akinator.network.Result
import java.io.IOException
import java.lang.Exception

class DefaultRecognitionRepository(private val api: RecognitionApi) : RecognitionRepository{


    override suspend fun getSongByLyrics(lyrics: String): Result<Song> =
        when(val response = getApiResult(lyrics)) {
            is Result.Success ->{
                val result = response.data.result

                if(result.isNotEmpty()){
                    Result.Success(result[0].toSong())
                }else{
                    Result.Error(Exception("The search has not given any results"))
                }
            }
            is Result.Error -> {
                Result.Error(response.exception)
            }
            else -> Result.Error(IOException("Timed out Error"))
        }


    private suspend fun getApiResult(lyrics: String) : Result<ApiResponse>{

        val response = api.searchByLyrics(lyrics).await()

        if(response.isSuccessful)return Result.Success(response.body()!!)

        return Result.Error(IOException("Error Occurred during getting safe Api result"))
    }

}