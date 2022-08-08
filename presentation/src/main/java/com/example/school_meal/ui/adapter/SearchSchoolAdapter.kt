package com.example.school_meal.ui.adapter

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.school_meal.R
import com.example.domain.entity.SchoolEntity.SchoolInfo.InfoRow

class SearchSchoolAdapter(val itemlist: List<InfoRow>?, val itemClick : (InfoRow) -> Unit): RecyclerView.Adapter<SearchSchoolAdapter.ViewHolder>() {
    class ViewHolder(v: View): RecyclerView.ViewHolder(v) {
        fun bind(item: InfoRow, itemClick : (InfoRow) -> Unit){
            itemView.findViewById<TextView>(R.id.find_school_text).setBackgroundColor(Color.GRAY)
            itemView.findViewById<TextView>(R.id.find_school_text).text = item.schoolName
            itemView.findViewById<TextView>(R.id.find_school_text).setOnClickListener {
                itemClick(item)
                itemView.findViewById<TextView>(R.id.find_school_text).setBackgroundColor(Color.GREEN)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflateView = LayoutInflater.from(parent.context).inflate(R.layout.item_find_school, parent, false)
        return ViewHolder(inflateView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(itemlist!![position], itemClick)
    }

    override fun getItemCount(): Int {
        return itemlist?.size ?: 0
    }
}