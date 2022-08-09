package com.example.school_meal.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entity.MealEntity
import com.example.school_meal.R
import java.text.SimpleDateFormat
import java.util.*

class MealAdapter(
    val itemList: List<MealEntity.MealServiceDietInfo.DietRow>?,
    val context: Context?,
) : RecyclerView.Adapter<MealAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: MealEntity.MealServiceDietInfo.DietRow, context: Context) {
            var content: String = ""
            var date = Date()

            for (data in item.dishName) {
                if (!data.equals('<') && !data.equals('>') && !data.equals('/') && !data.equals('b') && !data.equals(
                        'r') && !data.equals('(') && !data.equals(')')
                    && !data.equals('0') && !data.equals('1') && !data.equals('2') && !data.equals(
                        '3') && !data.equals('4') && !data.equals('5')
                    && !data.equals('6') && !data.equals('7') && !data.equals('8') && !data.equals(
                        '9') && !data.equals('.') && !data.equals('*')
                ) {
                    content += data
                }
                if (data.equals('>')) {
                    content += "\n"
                }
            }
            itemView.findViewById<TextView>(R.id.school_meal_date_txt).text =  "${item.mealDay.slice(4..5)}월 ${item.mealDay.slice(6 until item.mealDay.length)}일"
            itemView.findViewById<TextView>(R.id.school_meal_txt).text = content
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflateView = LayoutInflater.from(parent.context).inflate(R.layout.item_find_meal, parent, false)
        return ViewHolder(inflateView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(itemList!![position], context!!)
    }

    override fun getItemCount(): Int {
        return itemList?.size ?: 0
    }
}