package com.example.finalprojectapi.entities

import com.example.finalprojectapi.api.TriviaApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val BASE_URL = "https://opentdb.com/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val triviaApi: TriviaApiService = retrofit.create(TriviaApiService::class.java)
}
