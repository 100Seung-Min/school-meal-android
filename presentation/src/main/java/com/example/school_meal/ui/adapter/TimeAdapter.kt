package com.example.school_meal.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entity.TimeEntity
import com.example.school_meal.R
import com.example.school_meal.databinding.ItemTimeBinding

class TimeAdapter(val itemList: List<TimeEntity.TimeDateRow>): RecyclerView.Adapter<TimeAdapter.ViewHolder>() {
    class ViewHolder(val binding: ItemTimeBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TimeEntity.TimeDateRow) {
            binding.timeItem = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemTimeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}