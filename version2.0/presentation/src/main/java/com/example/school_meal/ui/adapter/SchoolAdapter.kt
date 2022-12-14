package com.example.school_meal.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entity.SchoolInfoEntity.SchoolInfo
import com.example.school_meal.databinding.ItemSchoolBinding

class SchoolAdapter(val itemlist: List<SchoolInfo>?, val itemClick : (SchoolInfo) -> Unit): RecyclerView.Adapter<SchoolAdapter.ViewHolder>() {
    class ViewHolder(val binding: ItemSchoolBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SchoolInfo, itemClick : (SchoolInfo) -> Unit) = with(binding) {
            schoolItem = item
            findSchoolText.setOnClickListener {
                itemClick(item)
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