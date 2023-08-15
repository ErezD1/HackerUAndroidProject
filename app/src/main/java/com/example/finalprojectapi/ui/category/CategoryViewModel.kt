package com.example.finalprojectapi.ui.category

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalprojectapi.data.repository.TriviaRepository
import kotlinx.coroutines.launch

class CategoryViewModel(
    private val triviaRepository: TriviaRepository
) : ViewModel() {

    val categoriesList = MutableLiveData<List<String>>()
    private val _selectedCategory = MutableLiveData<String>()
    val selectedCategory: String
        get() = _selectedCategory.value ?: ""

    fun fetchCategories() {
        viewModelScope.launch {
            val categories = triviaRepository.fetchAllCategories()
            categoriesList.value = categories.map { it.name }
        }
    }

    fun setSelectedCategory(category: String) {
        _selectedCategory.value = category
    }
}
