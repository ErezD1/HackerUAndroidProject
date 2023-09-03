package com.example.finalprojectapi.ui.start

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import com.example.finalprojectapi.R
import com.example.finalprojectapi.ui.viewmodels.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WelcomeFragment : Fragment() {
    private val sharedViewModel: SharedViewModel by viewModels()

    private lateinit var nameInput: EditText
    private lateinit var welcomeText: TextView
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_welcome, container, false)

        nameInput = view.findViewById(R.id.nameInput)
        welcomeText = view.findViewById(R.id.welcomeText)
        val submitNameButton: Button = view.findViewById(R.id.submitNameButton)

        sharedPreferences =
            requireActivity().getSharedPreferences("TriviaApp", Context.MODE_PRIVATE)

        sharedViewModel.userName.observe(viewLifecycleOwner, Observer { name ->
            if (name != null && name.isNotEmpty()) {
                nameInput.setText(name)
                welcomeText.text = "Welcome to the Trivia App:\n$name"
            }
        })

        submitNameButton.setOnClickListener {
            val name = nameInput.text.toString()
            welcomeText.text = "Welcome to the Trivia App:\n$name"

            with(sharedPreferences.edit()) {
                putString("userName", name)
                apply()
            }

            // Update the name in SharedViewModel
            sharedViewModel.updateUserName(name)

            // Close the keyboard
            val imm =
                requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)

            findNavController().navigate(R.id.action_welcomeFragment_to_categoryFragment)
        }

        // Moved this outside of the above block
        nameInput.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                val name = nameInput.text.toString()
                welcomeText.text = "Welcome to the Trivia App:\n$name"

                with(sharedPreferences.edit()) {
                    putString("userName", name)
                    apply()
                }

                // Update the name in SharedViewModel
                sharedViewModel.updateUserName(name)

                // Close the keyboard
                val imm =
                    requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view.windowToken, 0)

                true  // Handled
            } else {
                false  // Not handled
            }
        }
        return view
    }
}


