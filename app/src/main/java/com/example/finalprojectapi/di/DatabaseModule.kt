package com.example.finalprojectapi.di

import android.content.Context
import com.example.finalprojectapi.entities.AppDatabase
import com.example.finalprojectapi.dao.CategoryDao
import com.example.finalprojectapi.dao.QuestionDao
import com.example.finalprojectapi.dao.ScoreBoardDao
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
    fun provideTriviaDatabase(@ApplicationContext context: Context): AppDatabase =
        AppDatabase.getDatabase(context)

    @Provides
    @Singleton
    fun provideCategoryDao(database: AppDatabase): CategoryDao = database.categoryDao()

    @Provides
    @Singleton
    fun provideQuestionDao(database: AppDatabase): QuestionDao = database.questionDao()

    @Provides
    @Singleton
    fun provideScoreBoardDao(database: AppDatabase): ScoreBoardDao = database.scoreBoardDao()
}
