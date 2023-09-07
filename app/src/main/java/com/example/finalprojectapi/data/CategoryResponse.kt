package com.example.finalprojectapi.data

import com.example.finalprojectapi.model.Category
import com.google.gson.annotations.SerializedName

data class CategoryResponse(
    @SerializedName("trivia_categories") val categories: List<Category>
)
