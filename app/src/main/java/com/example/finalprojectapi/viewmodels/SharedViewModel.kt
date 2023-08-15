package com.example.finalprojectapi.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.finalprojectapi.entities.Category
import com.example.finalprojectapi.repository.TriviaRepository
import javax.inject.Inject

class SharedViewModel @Inject constructor() : ViewModel() {
    val selectedCategory: MutableLiveData<String> = MutableLiveData()
    private val _selectedCategory = MutableLiveData<String>()
    private val _selectedDifficulty = MutableLiveData<String>()
    val selectedDifficulty = MutableLiveData<String>()
    var userAnswerResult: MutableLiveData<String> = MutableLiveData()

    fun selectCategory(category: String) {
        _selectedCategory.value = category
    }

    fun selectDifficulty(difficulty: String) {
        _selectedDifficulty.value = difficulty
    }
}
