package com.example.school_meal.ui.component.meal

import android.view.View
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
import java.util.*

class MealMonthFragment : BaseFragment<FragmentMealMonthBinding>(R.layout.fragment_meal_month) {
    private val mealViModel by activityViewModels<MealViewModel>()
    private lateinit var adapter: MealAdapter
    override fun init() {
        binding.meal = this
        initList()
        mealViModel.meal()
        observeMeal()
        initMonthTxt()
        mealViModel.currentMonth.observe(this) {
            println("안녕 $it")
            binding.monthTxt.text = "${it.slice(0..3)}년 ${it.slice(4..5)}월"
            mealViModel.meal()
        }
    }

    private fun getDate(dateString: String): String {
        val date = SimpleDateFormat("yyyyMMdd").parse(dateString)
        return SimpleDateFormat("EE", Locale.KOREA).format(date)
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

    private fun initMonthTxt() {
        binding.monthTxt.text =
            "${mealViModel.currentMonth.value!!.slice(0..3)}년 ${mealViModel.currentMonth.value!!.slice(4..5)}월"
    }

    private fun observeMeal() = mealViModel.mealInfo.observe(this) { item ->
        if (item.isNotEmpty()) {
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
        } else {
            adapter.submitList(item)
        }
    }

    fun changeMonth(view: View) {
        if (view.id == R.id.nextBtn) {
            mealViModel.setMonth()
        } else {
            mealViModel.setMonth(false)
        }
    }
}