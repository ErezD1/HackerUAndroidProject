package com.example.finalprojectapi.ui.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.finalprojectapi.R
import com.example.finalprojectapi.databinding.FragmentCategoryBinding
import com.example.finalprojectapi.viewmodels.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryFragment : Fragment() {

    private var _binding: FragmentCategoryBinding? = null
    private val binding get() = _binding!!
    private val SharedViewModel: SharedViewModel by viewModels()

    private var selectedCategory: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Fetch categories from API
        SharedViewModel.getCategories()

        // Observe the categories LiveData
        SharedViewModel.categories.observe(viewLifecycleOwner) { categories ->
            categories?.let {
                val categoryNames = it.map { category -> category.name }
                val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, categoryNames)
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                binding.categorySpinner.adapter = adapter
            }
        }

        // Set an item selected listener for the Spinner
        binding.categorySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedCategory = binding.categorySpinner.selectedItem.toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Do nothing
            }
        }

        // Set up the select button click listener
        binding.btnSelectCategory.setOnClickListener {
            selectedCategory?.let { category ->
                Toast.makeText(requireContext(), "Selected: $category", Toast.LENGTH_SHORT).show()
                sharedViewModel.selectedCategory.value = category
                findNavController().navigate(R.id.actionCategoryFragmentToDifficultyFragment)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
