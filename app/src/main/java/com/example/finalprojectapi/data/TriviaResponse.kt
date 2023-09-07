package com.example.finalprojectapi.data

import com.example.finalprojectapi.model.Question
import com.google.gson.annotations.SerializedName

data class TriviaResponse(
    @SerializedName("response_code") val responseCode: Int,
    @SerializedName("results") val questions: List<Question>
)