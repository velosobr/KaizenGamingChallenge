package com.velosobr.kaizengaming.presentation.adapter

import android.content.Context
import android.os.Handler
import android.os.Looper
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
import java.util.Timer
import kotlin.concurrent.timerTask

class EventAdapter(
    private val events: List<Event>,
    context: Context
) :
    RecyclerView.Adapter<EventAdapter.EventViewHolder>() {

    private val sharedPreferences = context.getSharedPreferences("favorites", Context.MODE_PRIVATE)
    private val handler = Handler(Looper.getMainLooper())
    private lateinit var runnable: Runnable

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
        private var timer: Timer? = null

        fun bind(event: Event) {
            binding.tvCompetitorFirst.text = event.competitors[0]
            binding.tvCompetitorSecond.text = event.competitors[1]

            var countdownTime = event.tt.toLong()
            timer?.cancel() // Cancel any existing timer
            timer = Timer().apply {
                schedule(timerTask {
                    val countdown = TimeUtils.convertUnixToTime(countdownTime)
                    binding.tvCountdown.post {
                        binding.tvCountdown.text = countdown
                    }
                    countdownTime--
                }, 0, 1000)
            }
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

    override fun onViewRecycled(holder: EventViewHolder) {
        handler.removeCallbacks(runnable)
        super.onViewRecycled(holder)
    }
    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        handler.removeCallbacks(runnable)
        super.onDetachedFromRecyclerView(recyclerView)
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

        val index = events.indexOf(event)
        if (index != -1) {
            notifyItemChanged(index)
        }
    }
}