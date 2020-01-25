package com.example.akinator.network

import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PreviewApi {

    @GET("search/")
    suspend fun searchTrack(@Query("q") titleAndArtist: String): Response<PreviewApiResponse>

}