package com.example.school_meal.ui.component.meal

import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.data.remote.response.MealResponse
import com.example.domain.entity.MealEntity
import com.example.school_meal.R
import com.example.school_meal.databinding.FragmentMealMonthBinding
import com.example.school_meal.ui.adapter.MealAdapter
import com.example.school_meal.ui.component.base.BaseFragment
import com.example.school_meal.viewmodel.MealViewModel
import java.text.SimpleDateFormat
import java.util.Date

class MealMonthFragment : BaseFragment<FragmentMealMonthBinding>(R.layout.fragment_meal_month) {
    private val mealViModel by activityViewModels<MealViewModel>()
    private lateinit var adapter: MealAdapter
    override fun init() {
        initList()
        mealViModel.meal("202212")
        mealViModel.mealInfo.observe(this) { item ->
            var list = when (getDate(item[0].mealDay)) {
                "월" -> listOf()
                "화" -> addList(1)
                "수" -> addList(2)
                "목" -> addList(3)
                "금" -> addList(4)
                else -> listOf()
            }
            item.forEach {
                if (it.mealTime == mealViModel.currentMeal.value) {
                    list = list.plus(it)
                    if (mealViModel.currentMeal.value == "석식" && getDate(it.mealDay) == "목") {
                        list = list.plus(
                            MealEntity.MealItem(
                                "집 가는 날",
                                (it.mealDay.toInt() + 1).toString(),
                                "석식"
                            )
                        )
                    }
                }
            }
            adapter.submitList(list)
        }
    }

    private fun getDate(dateString: String): String {
        val date = SimpleDateFormat("yyyyMMdd").parse(dateString)
        return SimpleDateFormat("EE").format(date)
    }

    private fun addList(count: Int): List<MealEntity.MealItem> {
        var list = listOf<MealEntity.MealItem>()
        for (i in 0 until count) list = list.plus(
            MealEntity.MealItem(
                "",
                "",
                mealViModel.currentMeal.value ?: "조식"
            )
        )
        return list
    }

    private fun initList() {
        adapter = MealAdapter()
        binding.mealList.apply {
            adapter = this@MealMonthFragment.adapter
            layoutManager = GridLayoutManager(context, 5)
        }
    }
}