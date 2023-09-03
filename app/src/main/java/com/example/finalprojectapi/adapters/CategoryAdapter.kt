package com.example.finalprojectapi.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.finalprojectapi.databinding.ItemCategoryBinding
import com.example.finalprojectapi.model.Category

class CategoryAdapter(private val onCategoryClick: (Category) -> Unit) :
    ListAdapter<Category, CategoryAdapter.ViewHolder>(CategoryDiffCallback())
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category = getItem(position)
        holder.bind(category)
    }

    inner class ViewHolder(private val binding: ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val category = getItem(position)
                    onCategoryClick(category)
                }
            }
        }

        fun bind(category: Category) {
            binding.category = category

            // Map category name to the appropriate icon resource name or URL
            val iconResourceName = when (category.name) {
                "General Knowledge" -> "questionmark"
                "Entertainment: Books" -> "books"
                "Entertainment: Film" -> "filmreel"
                "Entertainment: Music" -> "musicalnotes"
                "Entertainment: Musicals & Theatres" -> "theatremask"
                "Entertainment: Television" -> "retrotv"
                "Entertainment: Video Games" -> "joystick"
                "Entertainment: Board Games" -> "boardgame"
                "Science & Nature" -> "testtube"
                "Science: Computers" -> "math"
                "Science: Mathematics" -> "math"
                "Mythology" -> "trojanhorse"
                "Sports" -> "sportsmode"
                "Geography" -> "globe"
                "History" -> "Trojan_Horse"
                "Politics" -> "parliament"
                "Art" -> "modernart"
                "Celebrities" -> "star"
                "Animals" -> "racoon"
                "Vehicles" -> "sportutilityvehicle"
                "Entertainment: Comics" -> "comicbook"
                "Science: Gadgets" -> "gadgets"
                "Entertainment: Japanese Anime & Manga" -> "naruto"
                "Entertainment: Cartoon & Animations" -> "ricksanchez"
                else -> "unknown"
            }

            // Get the resource ID for the icon
            val iconResourceId = itemView.context.resources.getIdentifier(
                iconResourceName, "drawable", itemView.context.packageName
            )

            // Load the icon into the ImageView using Glide
            Glide.with(itemView)
                .load(iconResourceId)
                .into(binding.categoryImage)

            binding.executePendingBindings()
        }
    }

    }

class CategoryDiffCallback : DiffUtil.ItemCallback<Category>() {
    override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem == newItem
    }
}
