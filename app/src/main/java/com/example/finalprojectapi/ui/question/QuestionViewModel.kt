package com.example.finalprojectapi.ui.question

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalprojectapi.data.model.Question
import com.example.finalprojectapi.data.repository.TriviaRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch

class QuestionViewModel @AssistedInject constructor(
    val repository: TriviaRepository,
    @Assisted("category") categoryId: Int,
    @Assisted("difficulty") difficulty: String
) : ViewModel() {

    val questionsLiveData: LiveData<List<Question>> = MutableLiveData()

    init {
        viewModelScope.launch {
            val questions = repository.fetchAndCacheQuestions(categoryId, difficulty, 10, "multiple")
            (questionsLiveData as MutableLiveData).postValue(questions)
        }
    }
}
