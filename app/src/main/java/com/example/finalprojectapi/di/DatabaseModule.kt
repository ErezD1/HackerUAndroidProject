package com.example.finalprojectapi.di

import android.content.Context
import com.example.finalprojectapi.entities.TriviaDatabase
import com.example.finalprojectapi.repository.CategoryDao
import com.example.finalprojectapi.repository.QuestionDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideTriviaDatabase(@ApplicationContext context: Context): TriviaDatabase =
        TriviaDatabase.getDatabase(context)

    @Provides
    @Singleton
    fun provideCategoryDao(database: TriviaDatabase): CategoryDao = database.categoryDao()

    @Provides
    @Singleton
    fun provideQuestionDao(database: TriviaDatabase): QuestionDao = database.questionDao()
}
