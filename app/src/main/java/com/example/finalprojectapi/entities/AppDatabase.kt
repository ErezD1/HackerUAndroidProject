package com.example.finalprojectapi.entities

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.finalprojectapi.converter.Converters
import com.example.finalprojectapi.model.Category
import com.example.finalprojectapi.model.Question
import com.example.finalprojectapi.dao.QuestionDao
import com.example.finalprojectapi.dao.CategoryDao
import com.example.finalprojectapi.dao.ScoreBoardDao
import com.example.finalprojectapi.model.ScoreBoard

@TypeConverters(Converters::class)
@Database(entities = [Category::class, Question::class, ScoreBoard::class], version = 2, exportSchema = false )
abstract class AppDatabase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
    abstract fun scoreBoardDao(): ScoreBoardDao
    abstract fun questionDao(): QuestionDao

    companion object {
        @Volatile private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build().also { instance = it }
            }
        }
    }
}
