package com.example.school_meal.ui.component.meal

import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.domain.entity.MealEntity
import com.example.school_meal.R
import com.example.school_meal.databinding.FragmentMealMonthBinding
import com.example.school_meal.ui.adapter.MealAdapter
import com.example.school_meal.ui.component.base.BaseFragment
import com.example.school_meal.ui.extension.repeatOnStart
import com.example.school_meal.viewmodel.MealViewModel
import java.text.SimpleDateFormat
import java.util.*

class MealMonthFragment : BaseFragment<FragmentMealMonthBinding>(R.layout.fragment_meal_month) {
    private val mealViModel by activityViewModels<MealViewModel>()
    private lateinit var adapter: MealAdapter
    override fun init() {
        binding.meal = this
        initList()
        initMonthTxt()
        mealViModel.meal()
        repeatOnStart {
            mealViModel.eventFlow.collect { event -> handleEvent(event) }
        }
    }

    private fun handleEvent(event: MealViewModel.Event) = when(event) {
        is MealViewModel.Event.Meal -> {
            if (event.mealList != null && event.mealList.isNotEmpty()) {
                var list = when (getDate(event.mealList[0].mealDay)) {
                    "월" -> listOf()
                    "화" -> addList(1)
                    "수" -> addList(2)
                    "목" -> addList(3)
                    "금" -> addList(4)
                    else -> listOf()
                }
                event.mealList.forEach {
                    if (it.mealTime == MealViewModel.currentMeal) {
                        list = list.plus(it)
                        if (MealViewModel.currentMeal == "석식" && getDate(it.mealDay) == "목") {
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
                adapter.submitList(event.mealList)
            }
        }
        is MealViewModel.Event.MealDate -> {
            binding.monthTxt.text = "${event.date.slice(0..3)}년 ${event.date.slice(4..5)}월"
            mealViModel.meal()
        }
        is MealViewModel.Event.MealTime -> {
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
                MealViewModel.currentMeal
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
            "${MealViewModel.currentMonth.slice(0..3)}년 ${MealViewModel.currentMonth.slice(4..5)}월"
    }

    fun changeMonth(view: View) {
        if (view.id == R.id.nextBtn) {
            mealViModel.setMonth()
        } else {
            mealViModel.setMonth(false)
        }
    }
}