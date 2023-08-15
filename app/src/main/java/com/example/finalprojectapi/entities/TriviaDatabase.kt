package com.example.finalprojectapi.entities

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.finalprojectapi.converter.Converters
import com.example.finalprojectapi.repository.CategoryDao
import com.example.finalprojectapi.repository.QuestionDao

@Database(entities = [Category::class, Question::class], version = 2, exportSchema = false)
@TypeConverters(Converters::class)
abstract class TriviaDatabase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
    abstract fun questionDao(): QuestionDao

    companion object {
        // Singleton instance of database
        @Volatile
        private var INSTANCE: TriviaDatabase? = null

        fun getDatabase(context: Context): TriviaDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TriviaDatabase::class.java,
                    "trivia_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
