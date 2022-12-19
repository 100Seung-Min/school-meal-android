package com.example.school_meal.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entity.TimeEntity
import com.example.school_meal.databinding.ItemTimeBinding

class TimeAdapter : ListAdapter<List<TimeEntity>, TimeAdapter.TimeViewHolder>(diffUtil) {
    class TimeViewHolder(val binding: ItemTimeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: List<TimeEntity>) {
            listOf(
                binding.firstTxt,
                binding.secondTxt,
                binding.thirdTxt,
                binding.forthTxt,
                binding.fifthTxt,
                binding.sixthTxt,
                binding.seventhTxt
            ).forEachIndexed { i, view ->
                view.text = item[i].name
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeViewHolder {
        return TimeViewHolder(
            ItemTimeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TimeViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<List<TimeEntity>>() {
            override fun areItemsTheSame(
                oldItem: List<TimeEntity>,
                newItem: List<TimeEntity>
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: List<TimeEntity>,
                newItem: List<TimeEntity>
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}