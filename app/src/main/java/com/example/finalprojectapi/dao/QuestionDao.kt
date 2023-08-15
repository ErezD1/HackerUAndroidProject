package com.example.finalprojectapi.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.finalprojectapi.data.model.Question

@Dao
interface QuestionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(questions: List<Question>)

    @Query("SELECT * FROM questions WHERE category = :category AND difficulty = :difficulty")
    suspend fun getQuestionsForCategoryAndDifficulty(category: Int, difficulty: String): List<Question>

    @Query("DELETE FROM questions WHERE category = :category AND difficulty = :difficulty")
    suspend fun deleteQuestionsForCategoryAndDifficulty(category: Int, difficulty: String)
}
