package com.velosobr.kaizengaming.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.velosobr.kaizengaming.R
import com.velosobr.kaizengaming.databinding.ItemSportBinding
import com.velosobr.kaizengaming.domain.model.Sport

class SportAdapter(
    private val sports: MutableList<Sport>,
    context: Context
) : RecyclerView.Adapter<SportAdapter.SportViewHolder>() {

    private val sharedPreferences =
        context.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SportViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_sport, parent, false)
        return SportViewHolder(view).apply {
            binding.rvEvents.layoutManager =
                LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
            binding.rvEvents.adapter = EventAdapter(mutableListOf(), itemView.context)
        }
    }

    override fun onBindViewHolder(holder: SportViewHolder, position: Int) {
        holder.bind(sports[position])
    }

    override fun getItemCount() = sports.size

    inner class SportViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemSportBinding.bind(itemView)

        fun bind(sport: Sport) {
            binding.tvSportName.text = sport.d
            (binding.rvEvents.adapter as EventAdapter).updateEvents(sport.e)

            binding.ivExpandCollapse.setOnClickListener {
                toggleEventVisibility()
            }
            binding.toggleFavorite.setOnCheckedChangeListener { _, isChecked ->
                updateFavoriteEvents(isChecked, sport)
            }
        }

        private fun toggleEventVisibility() {
            if (binding.rvEvents.visibility == View.VISIBLE) {
                binding.rvEvents.visibility = View.GONE
                binding.ivExpandCollapse.setImageResource(R.drawable.baseline_arrow_drop_up_24)
            } else {
                binding.rvEvents.visibility = View.VISIBLE
                binding.ivExpandCollapse.setImageResource(R.drawable.outline_arrow_drop_down_24)
            }
        }

        private fun updateFavoriteEvents(isChecked: Boolean, sport: Sport) {
            val eventAdapter = binding.rvEvents.adapter as EventAdapter
            if (isChecked) {
                val favorites = sharedPreferences.getStringSet("favorites", mutableSetOf()) ?: mutableSetOf()
                val favoriteEvents = sport.e.filter { favorites.contains(it.i) }
                eventAdapter.updateEvents(favoriteEvents)
            } else {
                eventAdapter.updateEvents(sport.e)
            }
        }
    }
}