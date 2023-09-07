package com.example.finalprojectapi.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.finalprojectapi.model.ScoreBoard
import com.example.finalprojectapi.R

class ScoreboardAdapter(private var scores: List<ScoreBoard>) :
    RecyclerView.Adapter<ScoreboardAdapter.ScoreboardViewHolder>() {

    class ScoreboardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val username: TextView = itemView.findViewById(R.id.tv_username)
        val date: TextView = itemView.findViewById(R.id.tv_date)
        val category: TextView = itemView.findViewById(R.id.tv_category)
        val difficulty: TextView = itemView.findViewById(R.id.tv_difficulty)
        val score: TextView = itemView.findViewById(R.id.tv_score)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScoreboardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_scoreboard, parent, false)
        return ScoreboardViewHolder(view)
    }

    override fun onBindViewHolder(holder: ScoreboardViewHolder, position: Int) {
        val currentItem = scores[position]
        holder.username.text = currentItem.username
        holder.date.text = currentItem.date
        holder.category.text = currentItem.category
        holder.difficulty.text = currentItem.difficulty
        holder.score.text = currentItem.score.toString()
    }

    override fun getItemCount(): Int {
        return scores.size
    }

    fun updateScores(newScores: List<ScoreBoard>) {
        scores = newScores
        notifyDataSetChanged()
    }
}
