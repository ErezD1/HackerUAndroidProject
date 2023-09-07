package com.example.finalprojectapi.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.finalprojectapi.converter.Converters
import com.google.gson.annotations.SerializedName

@Entity(tableName = "questions")
data class Question(
    @PrimaryKey(autoGenerate = true) val id: Int = 0, // Auto generate ID
    @SerializedName("category") val category: String,
    @SerializedName("question") val question: String,
    @SerializedName("type") val type: String,
    @SerializedName("difficulty") val difficulty: String,
    @SerializedName("correct_answer") val correctAnswer: String,
    @TypeConverters(Converters::class)
    @SerializedName("incorrect_answers") val incorrectAnswers: List<String>
)
