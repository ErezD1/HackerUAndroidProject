package com.example.finalprojectapi.ui.difficulty

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DifficultyViewModel @Inject constructor() : ViewModel() {

    // A list of available difficulty levels. This can be fetched from an API or be static.
    val difficultyLevels = listOf("Easy", "Medium", "Hard")

    // LiveData to hold the selected difficulty level.
    private val selectedDifficulty = MutableLiveData<String>()

    fun selectDifficulty(difficulty: String) {
        selectedDifficulty.value = difficulty
    }
}
