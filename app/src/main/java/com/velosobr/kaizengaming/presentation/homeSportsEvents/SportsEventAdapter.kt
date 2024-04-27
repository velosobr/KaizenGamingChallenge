package com.velosobr.kaizengaming.presentation.homeSportsEvents

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.velosobr.kaizengaming.R
import com.velosobr.kaizengaming.domain.model.Sport

class SportEventAdapter(private val sports: List<Sport>) : RecyclerView.Adapter<SportEventAdapter.SportEventViewHolder>() {

    inner class SportEventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvSportType: TextView = itemView.findViewById(R.id.tvSportType)
        val tvCompetitors: TextView = itemView.findViewById(R.id.tvCompetitors)
        val tvCountdown: TextView = itemView.findViewById(R.id.tvCountdown)
        val btnFavorite: ImageButton = itemView.findViewById(R.id.btnFavorite)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SportEventViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_sport_event, parent, false)
        return SportEventViewHolder(view)
    }

    override fun onBindViewHolder(holder: SportEventViewHolder, position: Int) {
        val sport = sports[position]
        holder.tvSportType.text = sport.type
        holder.tvCompetitors.text = sport.competitors.joinToString(", ")
        // TODO: Configure o cronômetro regressivo e o botão de favorito
    }

    override fun getItemCount() = sports.size
}