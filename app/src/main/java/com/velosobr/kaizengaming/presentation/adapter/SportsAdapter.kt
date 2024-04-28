package com.velosobr.kaizengaming.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.velosobr.kaizengaming.R
import com.velosobr.kaizengaming.databinding.ItemSportBinding
import com.velosobr.kaizengaming.domain.model.Sport


class SportAdapter(private val sports: MutableList<Sport>) :
    RecyclerView.Adapter<SportAdapter.SportViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SportViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_sport, parent, false)
        return SportViewHolder(view)
    }

    override fun onBindViewHolder(holder: SportViewHolder, position: Int) {
        holder.bind(sports[position])
    }

    override fun getItemCount() = sports.size

    class SportViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemSportBinding.bind(itemView)

        fun bind(sport: Sport) {
            binding.tvSportName.text = sport.d
            binding.rvEvents.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
            binding.rvEvents.adapter = EventAdapter(sport.e, itemView.context)

            binding.ivExpandCollapse.setOnClickListener {
                if (binding.rvEvents.visibility == View.VISIBLE) {
                    binding.rvEvents.visibility = View.GONE
                    binding.ivExpandCollapse.setImageResource(R.drawable.baseline_arrow_drop_up_24)
                } else {
                    binding.rvEvents.visibility = View.VISIBLE
                    binding.ivExpandCollapse.setImageResource(R.drawable.outline_arrow_drop_down_24)
                }
            }
        }
    }
}