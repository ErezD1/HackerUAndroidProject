package com.example.finalprojectapi.di

import com.example.finalprojectapi.entities.ApiClient
import com.example.finalprojectapi.api.TriviaApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    @Provides
    @Singleton
    fun provideTriviaApiService(): TriviaApiService = ApiClient.triviaApi
}
