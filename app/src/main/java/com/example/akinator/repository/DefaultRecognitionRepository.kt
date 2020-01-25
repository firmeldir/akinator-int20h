package com.example.akinator.repository

import android.util.Log
import com.example.akinator.model.Song
import com.example.akinator.model.SongPreview
import com.example.akinator.network.*
import java.io.IOException

class DefaultRecognitionRepository(
    private val api: RecognitionApi,
    private val previewApi: PreviewApi
) : RecognitionRepository {


    override suspend fun getSongByLyrics(lyrics: String): Result<Song> =
        when (val response = getApiResult(lyrics)) {
            is Result.Success -> {
                val result = response.data.result

                if (result.isNotEmpty()) {
                    Result.Success(result[0].toSong())
                } else {
                    Result.Error(Exception("The search has not given any results"))
                }
            }
            is Result.Error -> {
                Result.Error(response.exception)
            }
            else -> Result.Error(IOException("Timed out Error"))
        }

    override suspend fun getSongPreview(song: Song) =
        when (val response = getPreviewResult(song.title, song.artist)) {
            is Result.Success -> {
                val result = response.data.data

                if (result.isNotEmpty()) Result.Success(result[0].toSongPreview())
                else Result.Error(Exception("The search has not given any results"))
            }

            is Result.Error -> Result.Error(response.exception)
            else -> Result.Error(IOException("Timed out Error"))
        }


    private suspend fun getApiResult(lyrics: String): Result<ApiResponse> {

        val response = api.searchByLyrics(lyrics).await()

        if (response.isSuccessful) return Result.Success(response.body()!!)

        return Result.Error(IOException("Error Occurred during getting safe Api result"))
    }

    private suspend fun getPreviewResult(songTitle: String, artistName: String) = try {
        val response =
            previewApi.searchTrack("""track:"${songTitle}" artist:"${artistName}" bpm_max:1""")
                .await()
        if (response.isSuccessful) Result.Success(response.body()!!)
        else Result.Error(java.lang.Exception("The search has not given any results"))
    } catch (exception: Exception) {
        Result.Error(exception)
    }
}