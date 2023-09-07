    package com.example.finalprojectapi.repository

    import android.util.Log
    import androidx.lifecycle.LiveData
    import com.example.finalprojectapi.dao.CategoryDao
    import com.example.finalprojectapi.dao.QuestionDao
    import com.example.finalprojectapi.dao.ScoreBoardDao
    import com.example.finalprojectapi.data.TriviaResponse
    import com.example.finalprojectapi.model.Category
    import com.example.finalprojectapi.model.Question
    import com.example.finalprojectapi.model.ScoreBoard
    import com.example.finalprojectapi.network.ApiService
    import javax.inject.Injectd

    class TriviaRepository @Inject constructor(
        private val apiService: ApiService,
        private val categoryDao: CategoryDao,
        private val questionDao: QuestionDao,
        private val scoreBoardDao: ScoreBoardDao
    ) {
            // Database operations for Category
            private suspend fun getAllCategoriesFromDB(): List<Category> {
                return categoryDao.getCategories()
            }

            private suspend fun insertAllCategoriesToDB(categories: List<Category>) {
                categoryDao.insertAll(categories)
            }

            private suspend fun deleteAllCategoriesFromDB() {
                categoryDao.deleteAllCategories()
            }

            suspend fun insertScore(scoreBoard: ScoreBoard) {
                Log.d("TriviaRepository", "Inserting Score: $scoreBoard")
                scoreBoardDao.insert(scoreBoard)
            }

            fun getAllScores(): LiveData<List<ScoreBoard>> {
                Log.d("TriviaRepository", "Fetching All Scores")
                return scoreBoardDao.getAllScores()
            }
            // Database operations for Question

            private suspend fun getQuestionsForCategoryAndDifficultyFromDB(category: Int, difficulty: String): List<Question> {
                return questionDao.getQuestionsForCategoryAndDifficulty(category, difficulty)
            }

            private suspend fun insertAllQuestionsToDB(questions: List<Question>) {
                questionDao.insertAll(questions)
            }

            suspend fun deleteQuestionsForCategoryAndDifficultyFromDB(category: Int, difficulty: String) {
                questionDao.deleteQuestionsForCategoryAndDifficulty(category, difficulty)
            }

            suspend fun syncCategories() {
                try {
                    val apiResponse = apiService.getCategories()
                    if (apiResponse.isSuccessful) {
                        val apiCategories = apiResponse.body()?.categories ?: emptyList()
                        val dbCategories = getAllCategoriesFromDB()
                        if (apiCategories != dbCategories) {
                            deleteAllCategoriesFromDB()
                            insertAllCategoriesToDB(apiCategories)
                        }
                    } else {
                        // Handle API error case
                        Log.e("TriviaRepository", "API error: ${apiResponse.errorBody()}")
                    }
                } catch (e: Exception) {
                    // Handle any other errors
                    Log.e("TriviaRepository", "Error syncing categories: ${e.localizedMessage}")
                }
            }

            // New method that decides whether to fetch from API or DB
            suspend fun getQuestions(category: Int, difficulty: String): List<Question> {
                try {
                    val questions = getQuestionsForCategoryAndDifficultyFromDB(category, difficulty)
                    if (questions.isEmpty()) {
                        Log.d("TriviaRepository", "Fetching questions from API")
                        val apiResponse = apiService.getQuestions(10, category, difficulty, "multiple")
                        if (apiResponse.isSuccessful) {
                            val apiQuestions = apiResponse.body()?.questions ?: emptyList()
                            insertAllQuestionsToDB(apiQuestions)
                            return apiQuestions
                        } else {
                            Log.e("TriviaRepository", "API error: ${apiResponse.errorBody()}")
                            return emptyList()
                        }
                    }
                    Log.d("TriviaRepository", "Fetching questions from Database")
                    return questions
                } catch (e: Exception) {
                    Log.e("TriviaRepository", "Error fetching questions: ${e.localizedMessage}")
                    return emptyList()
                }
            }

        suspend fun clearAllScores() {
            Log.d("TriviaRepository", "Clearing All Scores")
            scoreBoardDao.deleteAll()
        }

            suspend fun clearQuestionsForCurrentSettings(categoryId: Int, difficulty: String) {
                questionDao.deleteQuestionsForCategoryAndDifficulty(categoryId, difficulty)
            }

            suspend fun fetchCategoriesFromDatabase(): List<Category> {
                return categoryDao.getCategories()
            }
    }
