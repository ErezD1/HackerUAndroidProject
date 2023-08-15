package com.example.finalprojectapi.ui.difficulty

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.finalprojectapi.R
import com.example.finalprojectapi.databinding.FragmentDifficultyBinding
import com.example.finalprojectapi.viewmodels.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DifficultyFragment : Fragment() {

    private var _binding: FragmentDifficultyBinding? = null
    private val binding get() = _binding!!
    val viewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDifficultyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set up click listeners for difficulty buttons
        binding.btnEasy.setOnClickListener {
            navigateToQuestionFragment("easy")
        }

        binding.btnMedium.setOnClickListener {
            navigateToQuestionFragment("medium")
        }

        binding.btnHard.setOnClickListener {
            navigateToQuestionFragment("hard")
        }
    }

    private fun navigateToQuestionFragment(difficulty: String) {
        // Assuming you have a category value somewhere, for example:
        val category = "SomeCategory"  // Replace this with the actual category value.

        // Navigate using the action ID directly without Safe Args
        val actionId = R.id.actionDifficultyFragmentToQuestionFragment
        val bundle = Bundle().apply {
            putString("difficulty", difficulty)
            putString("category", category)
        }
        findNavController().navigate(actionId, bundle)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
