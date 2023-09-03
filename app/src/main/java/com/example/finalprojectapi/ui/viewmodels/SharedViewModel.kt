package com.example.finalprojectapi.ui.viewmodels

import android.content.SharedPreferences
import android.icu.text.SimpleDateFormat
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalprojectapi.dao.CategoryDao
import com.example.finalprojectapi.dao.QuestionDao
import com.example.finalprojectapi.model.Category
import com.example.finalprojectapi.model.Question
import com.example.finalprojectapi.model.ScoreBoard
import com.example.finalprojectapi.repository.TriviaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val repository: TriviaRepository,
    private val sharedPreferences: SharedPreferences
) : ViewModel() {

    private val _categories = MutableLiveData<List<Category>>()
    val categories: LiveData<List<Category>> get() = _categories

    private val _difficulties = MutableLiveData<List<String>>()
    val difficulties: LiveData<List<String>> get() = _difficulties

    private val _selectedCategory = MutableLiveData<Category>()
    val selectedCategory: LiveData<Category> get() = _selectedCategory

    private val _selectedDifficulty = MutableLiveData<String>()
    val selectedDifficulty: LiveData<String> get() = _selectedDifficulty

    private val _questionsList = MutableLiveData<List<Question>>()
    val questionsList: LiveData<List<Question>> get() = _questionsList

    private val _questionIndex = MutableLiveData<Int>()
    val questionIndex: LiveData<Int> get() = _questionIndex

    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int> get() = _score
    val allScores: LiveData<List<ScoreBoard>> = repository.getAllScores()

    private val _userName = MutableLiveData<String?>()
    val userName: MutableLiveData<String?> get() = _userName

    var isScoreSaved = false  // Add this flag

    // Add this LiveData to hold error messages
    private val _errorMessages = MutableLiveData<String>()
    val errorMessages: LiveData<String> get() = _errorMessages

    init {
        viewModelScope.launch {
            try {
                Log.d("SharedViewModel", "Inside init block")
                repository.syncCategories()
                fetchCategoriesFromDatabase()
                fetchDifficulties()
                val storedName = sharedPreferences.getString("userName", "")
                Log.d("SharedViewModel", "Stored username: $storedName")
                if (storedName?.isNotEmpty() == true) {
                    _userName.value = storedName  // <-- make sure to set this
                }
            } catch (e: Exception) {
                _errorMessages.value = "An error occurred: ${e.localizedMessage}"
                Log.e("SharedViewModel", "Error in initialization: ${e.localizedMessage}")
            }
        }
    }


    fun updateUserName(newName: String) {
        Log.d("SharedViewModel", "Inside updateUserName function. NewName: $newName")
        _userName.value = newName

        // Save the username to SharedPreferences
        val editor = sharedPreferences.edit()
        editor.putString("userName", newName)
        editor.apply()
    }

    fun setSelectedCategory(category: Category) {
        _selectedCategory.value = category
    }

    fun setSelectedDifficulty(difficulty: String) {
        _selectedDifficulty.value = difficulty
    }

    fun startNewQuiz() {
        resetState()
        clearQuestionsForCurrentSettings()
        isScoreSaved = false  // Reset the flag when starting a new quiz
    }

    fun clearAllScores() {
        viewModelScope.launch {
            try {
                repository.clearAllScores()
            } catch (e: Exception) {
                _errorMessages.value = "An error occurred while clearing the scoreboard: ${e.localizedMessage}"
            }
        }
    }

    // Update your fetchQuestions to set error messages
    fun fetchQuestions(categoryId: Int, difficulty: String) {
        Log.d("SharedViewModel", "Fetching questions for CategoryId: $categoryId, Difficulty: $difficulty")
        viewModelScope.launch {
            try {
                val questions = repository.getQuestions(categoryId, difficulty)
                _questionsList.value = questions
                Log.d("SharedViewModel", "Questions fetched: $questions")
            } catch (e: Exception) {
                _errorMessages.value = "An error occurred: ${e.localizedMessage}"
                Log.e("SharedViewModel", "Error fetching questions: ${e.localizedMessage}")
            }
        }
    }


    //Clears questions for the current category and difficulty from the database.
    // Update this method
    fun clearQuestionsForCurrentSettings() {
        viewModelScope.launch {
            val categoryId = selectedCategory.value?.id
            val difficulty = selectedDifficulty.value?.toString()
            if (categoryId != null && difficulty != null) {
                repository.clearQuestionsForCurrentSettings(categoryId, difficulty)
            }
        }
    }

    // Update this method
    private fun fetchCategoriesFromDatabase() {
        viewModelScope.launch {
            try {
                val categoriesFromDb = repository.fetchCategoriesFromDatabase()
                if (categoriesFromDb.isNotEmpty()) {
                    _categories.value = categoriesFromDb
                }
            } catch (e: Exception) {
                Log.e("SharedViewModel", "Error while fetching categories from DB: ${e.message}")
            }
        }
    }


    // Return a Pair containing a Boolean indicating if the answer is correct and the correct answer (if wrong)
    fun checkAnswer(selectedAnswer: String): Pair<Boolean, String?> {
        Log.d("DEBUG", "Checking answer $selectedAnswer")
        val currentQuestion = _questionsList.value?.getOrNull(_questionIndex.value ?: 0)
        val isCorrect = currentQuestion?.correctAnswer == selectedAnswer
        Log.d("DEBUG", "Is answer correct: $isCorrect")
        if (isCorrect) {
            _score.value = (_score.value ?: 0) + 1
            Log.d("DEBUG", "New score: ${_score.value}")
        }
        moveToNextQuestion()
        return Pair(isCorrect, if (isCorrect) null else currentQuestion?.correctAnswer)
    }

    private fun moveToNextQuestion() {
        val nextIndex = (_questionIndex.value ?: 0) + 1
        if (nextIndex < (_questionsList.value?.size ?: 0)) {
            _questionIndex.value = nextIndex
        }
    }


    fun resetState() {
        // Reset the LiveData objects back to their initial state or some default values
        _selectedCategory.value = Category(0, "default")  // Provide default values
        _selectedDifficulty.value = ""  // replace with default difficulty if any
        _questionsList.value = emptyList()
        _questionIndex.value = 0
        _score.value = 0  // Reset the score here
    }

    enum class Difficulty(val value: String) {
        EASY("easy"), MEDIUM("medium"), HARD("hard"), MIXED("")
    }

    // Fetch difficulties (this could be hardcoded if they don't change)
    private fun fetchDifficulties() {
        _difficulties.value = listOf(Difficulty.EASY.value, Difficulty.MEDIUM.value, Difficulty.HARD.value, Difficulty.MIXED.value)
    }

    fun saveScore() {
        Log.d("SharedViewModel", "Inside saveScore function")
        val difficultyToDisplay = if (_selectedDifficulty.value.isNullOrEmpty()) {
            "Mixed"
        } else {
            _selectedDifficulty.value ?: "Unknown"
        }
        val scoreBoard = ScoreBoard(
            username = _userName.value ?: "Unknown",
            date = SimpleDateFormat("yyyy-MM-dd").format(Date()),
            category = _selectedCategory.value?.name ?: "Unknown",
            difficulty = difficultyToDisplay,
            score = _score.value ?: 0
        )
        viewModelScope.launch {
            Log.d("SharedViewModel", "Inside coroutine, Saving Score: $scoreBoard")
            repository.insertScore(scoreBoard)
        }
        isScoreSaved = true  // Set this flag to true after saving the score
    }

    fun isLastQuestion(): Boolean {
        val questionCount = _questionsList.value?.size ?: 0
        val currentIndex = _questionIndex.value ?: 0
        return currentIndex >= questionCount - 1
    }
}