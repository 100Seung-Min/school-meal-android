package com.example.school_meal.ui.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.school_meal.data.DTO.SchoolTimeDate
import com.example.school_meal.data.DTO.elsTimetable
import com.example.school_meal.data.DTO.hisTimetable
import com.example.school_meal.data.DTO.misTimetable
import com.example.school_meal.R

class SearchTimeAdapter(val itemlist: SchoolTimeDate, val schoolLevel: String): RecyclerView.Adapter<SearchTimeAdapter.ViewHolder>() {
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        fun bind(item: hisTimetable){
            if(item.row.size != 1){
                var content = ""
                for(data in item.row){
                    content += data.ITRT_CNTNT + "\n"
                }
                itemView.findViewById<TextView>(R.id.text123).text = content
            }
        }
        fun bind(item: misTimetable){
            if(item.row.size != 1){
                var content = ""
                for(data in item.row){
                    content += data.ITRT_CNTNT + "\n"
                }
                itemView.findViewById<TextView>(R.id.text123).text = content
            }
        }
        fun bind(item: elsTimetable){
            if(item.row.size != 1){
                var content = ""
                for(data in item.row){
                    content += data.ITRT_CNTNT + "\n"
                }
                itemView.findViewById<TextView>(R.id.text123).text = content
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context).inflate(R.layout.timetable_item, parent, false)
        return ViewHolder(layoutInflater)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(schoolLevel == "고등학교"){
            holder.bind(itemlist.hisTimetable[position])
        }
        else if(schoolLevel == "중학교"){
            holder.bind(itemlist.misTimetable[position])
        }
        else if(schoolLevel == "초등학교"){
            holder.bind(itemlist.elsTimetable[position])
        }
    }

    override fun getItemCount(): Int {
        if(schoolLevel == "고등학교"){
            return itemlist.hisTimetable.size
        }
        else if(schoolLevel == "중학교"){
            return itemlist.misTimetable.size
        }
        else if(schoolLevel == "초등학교"){
            return itemlist.elsTimetable.size
        }
        return 1
    }
}