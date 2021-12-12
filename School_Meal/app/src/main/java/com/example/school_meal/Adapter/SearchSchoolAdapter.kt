package com.example.school_meal.Adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.school_meal.DTO.infoRow
import com.example.school_meal.R

class SearchSchoolAdapter(val itemlist: ArrayList<infoRow>, val context: Context, val itemClick : (infoRow) -> Unit): RecyclerView.Adapter<SearchSchoolAdapter.ViewHolder>() {
    class ViewHolder(v: View): RecyclerView.ViewHolder(v) {
        fun bind(item: infoRow, context: Context, itemClick : (infoRow) -> Unit){
            itemView.findViewById<TextView>(R.id.find_school_text).setBackgroundColor(Color.GRAY)
            itemView.findViewById<TextView>(R.id.find_school_text).text = item.SCHUL_NM
            itemView.findViewById<TextView>(R.id.find_school_text).setOnClickListener {
                itemClick(item)
                itemView.findViewById<TextView>(R.id.find_school_text).setBackgroundColor(Color.GREEN)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflateView = LayoutInflater.from(parent.context).inflate(R.layout.find_school_item, parent, false)
        return ViewHolder(inflateView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(itemlist[position], context, itemClick)
    }

    override fun getItemCount(): Int {
        return itemlist.size
    }
}