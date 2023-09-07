package com.example.finalprojectapi.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "scoreboard")
data class ScoreBoard(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val username: String,
    val date: String,
    val category: String,
    val difficulty: String,
    val score: Int
)
