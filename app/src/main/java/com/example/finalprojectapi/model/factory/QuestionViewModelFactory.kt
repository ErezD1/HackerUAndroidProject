package com.example.finalprojectapi.model.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.finalprojectapi.repository.TriviaRepository
import com.example.finalprojectapi.ui.question.QuestionViewModel

@Suppress("UNCHECKED_CAST")
class QuestionViewModelFactory(
    private val repository: TriviaRepository,
    private val categoryId: Int,
    private val difficulty: String
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(QuestionViewModel::class.java)) {
            return QuestionViewModel(repository, categoryId, difficulty) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
