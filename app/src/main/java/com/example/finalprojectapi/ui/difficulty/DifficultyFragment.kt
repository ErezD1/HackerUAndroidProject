package com.example.finalprojectapi.ui.difficulty

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.finalprojectapi.R
import com.example.finalprojectapi.databinding.FragmentDifficultyBinding  // <-- Import for Data Binding
import com.example.finalprojectapi.ui.viewmodels.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DifficultyFragment : Fragment(R.layout.fragment_difficulty) {

    private var _binding: FragmentDifficultyBinding? = null
    private val binding get() = _binding!!
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Inflate the layout using data binding
        _binding = FragmentDifficultyBinding.bind(view)

        sharedViewModel.difficulties.observe(viewLifecycleOwner) { _ ->
            val clickListener = View.OnClickListener { v ->
                val difficulty = when (v.id) {
                    binding.btnEasy.id -> SharedViewModel.Difficulty.EASY
                    binding.btnMedium.id -> SharedViewModel.Difficulty.MEDIUM
                    binding.btnHard.id -> SharedViewModel.Difficulty.HARD
                    binding.btnMixed.id -> SharedViewModel.Difficulty.MIXED
                    else -> throw IllegalArgumentException("Invalid button clicked")
                }
                navigateToQuestionFragment(difficulty)
            }
            binding.btnEasy.setOnClickListener(clickListener)
            binding.btnMedium.setOnClickListener(clickListener)
            binding.btnHard.setOnClickListener(clickListener)
            binding.btnMixed.setOnClickListener(clickListener)
        }
    }


    private fun navigateToQuestionFragment(difficulty: SharedViewModel.Difficulty) {
        sharedViewModel.setSelectedDifficulty(difficulty.value)  // Pass the string representation
        findNavController().navigate(R.id.actionDifficultyFragmentToQuestionFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
