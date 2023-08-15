package com.example.finalprojectapi.api

import com.example.finalprojectapi.data.CategoryResponse
import com.example.finalprojectapi.data.TriviaResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface TriviaApiService {
    @GET("https://opentdb.com/api_category.php")
    suspend fun getCategories(): CategoryResponse

    @GET("api.php")
    suspend fun fetchQuestions(
        @Query("amount") amount: Int,
        @Query("category") category: Int,
        @Query("difficulty") difficulty: String,
        @Query("type") type: String
    ): TriviaResponse


}
