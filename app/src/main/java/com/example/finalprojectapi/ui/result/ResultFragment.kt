package com.example.finalprojectapi.ui.result

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.finalprojectapi.databinding.FragmentResultBinding
import com.example.finalprojectapi.ui.viewmodels.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.finalprojectapi.R
import com.example.finalprojectapi.adapters.ScoreboardAdapter


@AndroidEntryPoint
class ResultFragment : Fragment() {

    private var _binding: FragmentResultBinding? = null
    private val binding get() = _binding!!

    private val sharedViewModel: SharedViewModel by activityViewModels()
    private lateinit var scoreboardAdapter: ScoreboardAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true) // <-- Added this line to indicate this fragment has its own menu

        // Only save the score if it hasn't been saved yet
        if (!sharedViewModel.isScoreSaved) {
            sharedViewModel.saveScore()
        }

        // Initialize the RecyclerView and its adapter
        scoreboardAdapter = ScoreboardAdapter(emptyList())
        binding.recyclerViewScoreboard.adapter = scoreboardAdapter
        binding.recyclerViewScoreboard.layoutManager = LinearLayoutManager(context)

        // Observe the allScores LiveData
        sharedViewModel.allScores.observe(viewLifecycleOwner) { scores ->
            Log.d("ResultFragment", "Scores: $scores")
            // Update the RecyclerView
            scoreboardAdapter.updateScores(scores)
        }
        // Start New Quiz button
        binding.btnStartNewQuiz.setOnClickListener {
            findNavController().navigate(R.id.action_resultFragment_to_categoryFragment)
            sharedViewModel.startNewQuiz()
            sharedViewModel.isScoreSaved = false  // Reset the flag when starting a new quiz
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.overflow_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_clear_scoreboard -> {
                showClearScoreboardConfirmationDialog()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showClearScoreboardConfirmationDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle("Clear Scoreboard")
            .setMessage("Are you sure you want to clear the scoreboard?")
            .setPositiveButton("Yes") { _, _ ->
                sharedViewModel.clearAllScores() // Clear the scoreboard
            }
            .setNegativeButton("No", null)
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}