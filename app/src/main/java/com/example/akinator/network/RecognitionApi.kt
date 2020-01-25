package com.example.akinator.network

import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RecognitionApi {

    @GET("findLyrics/")
    fun searchByLyrics(@Query("q") q : String) : Deferred<Response<ApiResponse>>
}