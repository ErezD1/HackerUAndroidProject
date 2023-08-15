package com.example.finalprojectapi.ui.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.finalprojectapi.R
import com.example.finalprojectapi.databinding.FragmentCategoryBinding

class CategoryFragment : Fragment() {

    private val categoryViewModel: CategoryViewModel by activityViewModels()
    private var _binding: FragmentCategoryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        categoryViewModel.categoriesList.observe(viewLifecycleOwner) { categories ->
            val adapter =
                ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, categories)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.categorySpinner.adapter = adapter
        }

        binding.categorySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedCategory = binding.categorySpinner.selectedItem.toString()
                categoryViewModel.setSelectedCategory(selectedCategory)
            }
        }

        binding.btnSelectCategory.setOnClickListener {
            findNavController().navigate(R.id.actionCategoryFragmentToDifficultyFragment)
        }

        categoryViewModel.fetchCategories()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
