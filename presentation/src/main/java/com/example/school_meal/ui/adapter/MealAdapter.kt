package com.example.school_meal.ui.adapter

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entity.MealEntity
import com.example.school_meal.R
import com.example.school_meal.databinding.ItemMealBinding
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class MealAdapter(
    val itemList: List<MealEntity.MealServiceDietInfo.DietRow>?,
) : RecyclerView.Adapter<MealAdapter.ViewHolder>() {
    class ViewHolder(val binding: ItemMealBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MealEntity.MealServiceDietInfo.DietRow) {
            binding.mealItem = item
            var content = ""
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
            binding.schoolMealDateTxt.text =  "${item.mealDay.slice(4..5)}월 ${item.mealDay.slice(6 until item.mealDay.length)}일"
            binding.schoolMealTxt.text = content
            val today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"))
            if(today.equals(item.mealDay)) {
                binding.findMealLayout.setBackgroundColor(Color.GREEN)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemMealBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(itemList!![position])
    }

    override fun getItemCount(): Int {
        return itemList?.size ?: 0
    }
}