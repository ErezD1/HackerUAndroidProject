package com.example.finalprojectapi.network

import com.example.finalprojectapi.data.CategoryResponse
import com.example.finalprojectapi.data.TriviaResponse
import com.example.finalprojectapi.model.Question
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("api_category.php") // Corrected URL for getting categories
    suspend fun getCategories(): Response<CategoryResponse>

    @GET("https://opentdb.com/api.php?")
    suspend fun getQuestions(
        @Query("amount") amount: Int,
        @Query("category") category: Int,
        @Query("difficulty") difficulty: String,
        @Query("type") type: String
    ): Response<TriviaResponse>
}
