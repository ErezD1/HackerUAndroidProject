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
import androidx.recyclerview.widget.GridLayoutManager
import com.example.finalprojectapi.R
import com.example.finalprojectapi.adapters.CategoryAdapter
import com.example.finalprojectapi.databinding.FragmentCategoryBinding
import com.example.finalprojectapi.ui.viewmodels.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CategoryFragment : Fragment() {

    private var _binding: FragmentCategoryBinding? = null
    private val binding get() = _binding!!
    private val sharedViewModel: SharedViewModel by activityViewModels()

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

        // Initialize and setup RecyclerView and its adapter
        val categoryAdapter = CategoryAdapter { selectedCategory ->
            // Handle item clicks
            sharedViewModel.setSelectedCategory(selectedCategory)
            findNavController().navigate(R.id.actionCategoryFragmentToDifficultyFragment)
        }
        binding.categoryRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.categoryRecyclerView.adapter = categoryAdapter

        // Observe and submit the list to the adapter when it changes
        sharedViewModel.categories.observe(viewLifecycleOwner) { categories ->
            categoryAdapter.submitList(categories)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
