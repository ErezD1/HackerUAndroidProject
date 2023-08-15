package com.example.finalprojectapi.data

import com.example.finalprojectapi.entities.Category
import com.google.gson.annotations.SerializedName

data class CategoryResponse(
    @SerializedName("trivia_categories") val categories: List<Category>
)

