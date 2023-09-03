package com.example.finalprojectapi.ui.question

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.text.Spanned
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.finalprojectapi.R
import com.example.finalprojectapi.databinding.FragmentQuestionBinding
import com.example.finalprojectapi.model.Question
import com.example.finalprojectapi.ui.viewmodels.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale

@AndroidEntryPoint
class QuestionFragment : Fragment(R.layout.fragment_question) {

    private var _binding: FragmentQuestionBinding? = null
    private val binding get() = _binding!!
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQuestionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val categoryId = sharedViewModel.selectedCategory.value?.id
        val difficulty = sharedViewModel.selectedDifficulty.value

        if (categoryId != null && difficulty != null) {
            sharedViewModel.fetchQuestions(categoryId, difficulty)
        }

        // Observe questionsList
        sharedViewModel.questionsList.observe(viewLifecycleOwner) {
            updateQuestionUI(it, sharedViewModel.questionIndex.value ?: 0)
        }

        // Observe questionIndex
        sharedViewModel.questionIndex.observe(viewLifecycleOwner) {
            updateQuestionUI(sharedViewModel.questionsList.value, it)
        }

        // Handle answer submission when a radio button is selected
        binding.radioGroup.setOnCheckedChangeListener { _: RadioGroup, checkedId: Int ->
            if (checkedId != -1) {  // Check if any button is selected
                handleAnswerSubmission()
            }
        }
    }

    private fun handleAnswerSubmission() {
        val selectedAnswer = getSelectedAnswer()
        val (isAnswerCorrect, correctAnswer) = sharedViewModel.checkAnswer(selectedAnswer)

        if (isAnswerCorrect) {
            showToast("Correct!")
        } else {
            showToast("Incorrect! The correct answer is $correctAnswer.")
        }

        if (sharedViewModel.isLastQuestion()) {
            sharedViewModel.saveScore()
            findNavController().navigate(R.id.action_questionFragment_to_resultFragment)
        }
    }

    private fun getSelectedAnswer(): String {
        return when {
            binding.rbChoice1.isChecked -> binding.rbChoice1.text.toString()
            binding.rbChoice2.isChecked -> binding.rbChoice2.text.toString()
            binding.rbChoice3.isChecked -> binding.rbChoice3.text.toString()
            binding.rbChoice4.isChecked -> binding.rbChoice4.text.toString()
            else -> ""
        }
    }

    private fun updateQuestionUI(questions: List<Question>?, questionIndex: Int) {
        val question = questions?.getOrNull(questionIndex)
        question?.let {
            val decodedQuestion = fromHtml(it.question).toString()
            binding.tvQuestion.text = decodedQuestion

            val options = mutableListOf<String>().apply {
                addAll(it.incorrectAnswers)
                add(it.correctAnswer)
                shuffle()
            }

            binding.tvDifficulty.text = "Difficulty: ${it.difficulty.capitalize(Locale.ROOT)}"
            binding.tvQuestionIndex.text = "Question ${questionIndex + 1} of ${questions.size}"

            val radioButtons = listOf(
                binding.rbChoice1, binding.rbChoice2, binding.rbChoice3, binding.rbChoice4
            )

            options.forEachIndexed { index, option ->
                radioButtons[index].text = fromHtml(option)
            }

            radioButtons.forEach { it.isChecked = false }
        }
    }

    private var currentToast: Toast? = null
    private fun showToast(message: String) {
        currentToast?.cancel()

        val inflater = layoutInflater
        val layout = inflater.inflate(R.layout.custom_toast_layout, requireActivity().findViewById(R.id.custom_toast_message))
        val text: TextView = layout.findViewById(R.id.custom_toast_message)
        text.text = fromHtml(message)

        val toast = Toast(requireContext())
        //toast.setGravity(Gravity.CENTER, 0, 0)
        toast.duration = Toast.LENGTH_SHORT
        toast.view = layout
        toast.show()

        currentToast = toast
    }

    @Suppress("DEPRECATION")
    private fun fromHtml(html: String): Spanned {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY)
        } else {
            Html.fromHtml(html)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
