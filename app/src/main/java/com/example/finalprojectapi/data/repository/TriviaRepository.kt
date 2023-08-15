package com.example.finalprojectapi.data.repository

import com.example.finalprojectapi.data.model.Category
import com.example.finalprojectapi.data.model.Question
import com.example.finalprojectapi.entities.ApiClient
import com.example.finalprojectapi.api.TriviaApiService
import com.example.finalprojectapi.dao.CategoryDao
import com.example.finalprojectapi.dao.QuestionDao
import javax.inject.Inject

class TriviaRepository @Inject constructor(
    private val triviaApiService: TriviaApiService,
    private val categoryDao: CategoryDao,
    private val questionDao: QuestionDao
) {

    private val apiService: TriviaApiService = ApiClient.triviaApi

    suspend fun fetchAndCacheQuestions(categoryId: Int, difficulty: String, amount: Int, type: String): List<Question> {
        val localQuestions = questionDao.getQuestionsForCategoryAndDifficulty(categoryId, difficulty)
        if (localQuestions.isEmpty()) {
            val triviaResponse = apiService.fetchQuestions(amount, categoryId, difficulty, type)
            val questionsFromApi = triviaResponse.questions
            if (questionsFromApi.isNotEmpty()) {
                questionDao.insertAll(questionsFromApi)
                return questionsFromApi
            }
        }
        return localQuestions
    }

    suspend fun fetchQuestions(category: Int, difficulty: String) = questionDao.getQuestionsForCategoryAndDifficulty(category, difficulty)

    suspend fun cacheQuestions(questions: List<Question>) = questionDao.insertAll(questions)

    // Modify to check cache first
    suspend fun fetchAllCategories(): List<Category> {
        val localCategories = categoryDao.getAllCategories()
        if (localCategories.isEmpty()) {
            val categoriesFromApi = apiService.getCategories().categories
            categoryDao.insertAll(categoriesFromApi)
            return categoriesFromApi
        }
        return localCategories
    }

    private suspend fun getCategoryIdByName(categoryName: String): Int? {
        val categories = fetchAllCategories()
        return categories.firstOrNull { it.name == categoryName }?.id
    }

    suspend fun fetchAndCacheQuestionsByCategoryName(categoryName: String, difficulty: String, amount: Int, type: String): List<Question> {
        val categoryId = getCategoryIdByName(categoryName) ?: return emptyList()
        return fetchAndCacheQuestions(categoryId, difficulty, amount, type)
    }

    // Additional methods for category operations
    suspend fun cacheCategories(categories: List<Category>) = categoryDao.insertAll(categories)
    suspend fun clearAllCategories() = categoryDao.clearCategories()
}
