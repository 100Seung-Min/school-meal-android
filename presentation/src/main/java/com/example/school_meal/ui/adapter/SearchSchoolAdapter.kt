package com.example.school_meal.ui.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.school_meal.R
import com.example.domain.entity.SchoolEntity.SchoolInfo.InfoRow
import com.example.school_meal.databinding.ItemSchoolBinding

class SearchSchoolAdapter(val itemlist: List<InfoRow>?, val itemClick : (InfoRow) -> Unit): RecyclerView.Adapter<SearchSchoolAdapter.ViewHolder>() {
    class ViewHolder(val binding: ItemSchoolBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: InfoRow, itemClick : (InfoRow) -> Unit){
            with(binding) {
                schoolItem = item
                with(findSchoolText) {
                    setBackgroundColor(Color.GRAY)
                    setOnClickListener {
                        itemClick(item)
                        this.setBackgroundColor(Color.GREEN)
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemSchoolBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(itemlist!![position], itemClick)
    }

    override fun getItemCount(): Int {
        return itemlist?.size ?: 0
    }
}