package com.example.finalprojectapi.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.finalprojectapi.model.ScoreBoard

@Dao
interface ScoreBoardDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE) // or OnConflictStrategy.IGNORE
    suspend fun insert(scoreBoard: ScoreBoard)

    @Query("SELECT * FROM scoreboard ORDER BY date DESC")
    fun getAllScores(): LiveData<List<ScoreBoard>>

    @Query("DELETE FROM scoreboard")
    suspend fun deleteAll()
}
