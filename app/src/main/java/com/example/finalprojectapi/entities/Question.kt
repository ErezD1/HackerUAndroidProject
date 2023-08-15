package com.example.finalprojectapi.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "questions")
data class Question(
    @PrimaryKey
    val id: Int,
    val category: Int,
    val difficulty: String,
    val questionText: String,
    val correctAnswer: String,
    val incorrectAnswers: List<String>
)
