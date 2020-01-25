package com.example.akinator.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object PreviewApiFactory {

    private const val BASE_URL = "https://api.deezer.com/"

    private val interceptor = Interceptor { chain->
        val newUrl = chain.request().url()
            .newBuilder()
            .build()

        val newRequest = chain.request()
            .newBuilder()
            .url(newUrl)
            .build()

        chain.proceed(newRequest)
    }

    private val client = OkHttpClient().newBuilder()
        .addInterceptor(interceptor)
        .build()

    private fun retrofit() : Retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val previewApi : PreviewApi = retrofit().create(PreviewApi::class.java)
}