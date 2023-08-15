package com.example.finalprojectapi.ui.question

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.finalprojectapi.R
import com.example.finalprojectapi.databinding.FragmentQuestionBinding
import com.example.finalprojectapi.viewmodels.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuestionFragment : Fragment() {

    private var _binding: FragmentQuestionBinding? = null
    private val binding get() = _binding!!
    private val sharedViewModel: SharedViewModel by activityViewModels()
    private lateinit var questionViewModel: QuestionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQuestionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize the ViewModel
        questionViewModel = ViewModelProvider(this).get(QuestionViewModel::class.java)

        // Observe the questionsLiveData
        questionViewModel.questionsLiveData.observe(viewLifecycleOwner) { questions ->
            val question = questions.firstOrNull()
            question?.let {
                binding.tvQuestion.text = it.questionText
                val allAnswers = (it.incorrectAnswers + it.correctAnswer).shuffled()
                binding.rbChoice1.text = allAnswers[0]
                binding.rbChoice2.text = allAnswers[1]
                binding.rbChoice3.text = allAnswers[2]
                binding.rbChoice4.text = allAnswers[3]
            }
        }

        // Handle the answer submission
        binding.btnSubmit.setOnClickListener {
            val selectedAnswer = when (binding.rgChoices.checkedRadioButtonId) {
                binding.rbChoice1.id -> binding.rbChoice1.text.toString()
                binding.rbChoice2.id -> binding.rbChoice2.text.toString()
                binding.rbChoice3.id -> binding.rbChoice3.text.toString()
                binding.rbChoice4.id -> binding.rbChoice4.text.toString()
                else -> null
            }

            questionViewModel.questionsLiveData.value?.firstOrNull()?.let { question ->
                if (selectedAnswer == question.correctAnswer) {
                    sharedViewModel.userAnswerResult.value = "Correct!"
                } else {
                    sharedViewModel.userAnswerResult.value = "Incorrect!"
                }

                // Navigate to ResultFragment
                findNavController().navigate(R.id.action_questionFragment_to_resultFragment)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
