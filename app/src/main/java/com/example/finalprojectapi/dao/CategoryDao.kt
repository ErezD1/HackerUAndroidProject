package com.example.finalprojectapi.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.finalprojectapi.model.Category

@Dao
interface CategoryDao {
    @Query("SELECT * FROM categories")
    suspend fun getCategories(): List<Category>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(categories: List<Category>)

    @Query("DELETE FROM categories")
    suspend fun deleteAllCategories()

    @Query("DELETE FROM categories WHERE id = :id")
    suspend fun clearCategories(id: Int): Int // Change return type to Int

}
