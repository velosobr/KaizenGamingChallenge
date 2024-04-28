package com.velosobr.kaizengaming.presentation.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.velosobr.kaizengaming.R
import com.velosobr.kaizengaming.databinding.ItemSportEventBinding
import com.velosobr.kaizengaming.domain.model.Event
import com.velosobr.kaizengaming.utils.TimeUtils

class EventAdapter(
    private val events: List<Event>,
    context: Context
) :
    RecyclerView.Adapter<EventAdapter.EventViewHolder>() {

    private val sharedPreferences = context.getSharedPreferences("favorites", Context.MODE_PRIVATE)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_sport_event, parent, false)
        return EventViewHolder(view)
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        recyclerView.layoutManager =
            LinearLayoutManager(recyclerView.context, LinearLayoutManager.HORIZONTAL, false)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.bind(events[position])
    }

    override fun getItemCount() = events.size

    inner class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemSportEventBinding.bind(itemView)

        fun bind(event: Event) {
            binding.tvCompetitorFirst.text = event.competitors[0]
            binding.tvCompetitorSecond.text = event.competitors[1]
            binding.tvCountdown.text = TimeUtils.convertUnixToTime(event.tt.toLong())

            binding.imgFavorite.setOnClickListener {
                event.isFavorite = !event.isFavorite
                updateFavorites(event)
                notifyDataSetChanged()
            }

            if (event.isFavorite) {
                binding.imgFavorite.setImageResource(R.drawable.ic_favorite_filled)
                Log.d("EventAdapter", "Event ${event.i} is marked as favorite")
            } else {
                binding.imgFavorite.setImageResource(R.drawable.ic_favorite_border)
                Log.d("EventAdapter", "Event ${event.i} is not marked as favorite")
            }
        }
    }

    private fun updateFavorites(event: Event) {
        val favorites = sharedPreferences.getStringSet("favorites", mutableSetOf())?.toMutableSet()
            ?: mutableSetOf()
        if (event.isFavorite) {
            favorites.add(event.i)
            Log.d("EventAdapter", "Event ${event.i} added to favorites")
        } else {
            favorites.remove(event.i)
            Log.d("EventAdapter", "Event ${event.i} removed from favorites")
        }
        with(sharedPreferences.edit()) {
            putStringSet("favorites", favorites)
            apply()
        }
    }
}