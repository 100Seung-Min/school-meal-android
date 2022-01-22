package com.example.school_meal.ui.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.school_meal.data.DTO.dietRow
import com.example.school_meal.R
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class SearchMealAdapter(val itemlist: ArrayList<dietRow>, val context: Context?): RecyclerView.Adapter<SearchMealAdapter.ViewHolder>() {
    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        fun bind(item : dietRow, context: Context){
            var content: String = ""
            var date = Date()

            if(!item.MLSV_YMD.equals("")){
                date = SimpleDateFormat("yyyyMMdd").parse(item.MLSV_YMD)
                if(SimpleDateFormat("yyyyMMdd").format(date) == SimpleDateFormat("yyyyMMdd").format(Date())){
                    itemView.findViewById<ConstraintLayout>(R.id.find_meal_layout).setBackgroundColor(ContextCompat.getColor(context, R.color.yellow))
                }
            }

            for(data in item.DDISH_NM){
                if(!data.equals('<') && !data.equals('>') && !data.equals('/') && !data.equals('b') && !data.equals('r')
                    && !data.equals('0') && !data.equals('1') && !data.equals('2') && !data.equals('3') && !data.equals('4') && !data.equals('5')
                    && !data.equals('6') && !data.equals('7') && !data.equals('8') && !data.equals('9') && !data.equals('.') && !data.equals('*')){
                    content += data
                }
                if(data.equals('>')){
                    content += "\n"
                }
            }
            if(!item.MLSV_YMD.equals("")){
                itemView.findViewById<TextView>(R.id.school_meal_date_txt).text = SimpleDateFormat("dd일 EE요일").format(date).toString()
            }
            else{
                itemView.findViewById<TextView>(R.id.school_meal_date_txt).text = ""
            }
            itemView.findViewById<TextView>(R.id.school_meal_txt).text = content
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflateView = LayoutInflater.from(parent.context).inflate(R.layout.find_meal_item, parent, false)
        return ViewHolder(inflateView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(itemlist[position], context!!)
    }

    override fun getItemCount(): Int {
        return itemlist.size
    }
}