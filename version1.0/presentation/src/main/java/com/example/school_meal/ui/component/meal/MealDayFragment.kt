package com.example.school_meal.ui.component.meal

import android.view.View
import androidx.fragment.app.activityViewModels
import com.example.domain.entity.MealEntity
import com.example.school_meal.R
import com.example.school_meal.databinding.FragmentMealDayBinding
import com.example.school_meal.ui.component.base.BaseFragment
import com.example.school_meal.ui.extension.repeatOnStart
import com.example.school_meal.viewmodel.MealViewModel
import kotlinx.coroutines.flow.collect

class MealDayFragment : BaseFragment<FragmentMealDayBinding>(R.layout.fragment_meal_day) {
    private val mealViewModel by activityViewModels<MealViewModel>()
    override fun init() {
        binding.meal = this
        mealViewModel.meal()
        repeatOnStart {
            mealViewModel.eventFlow.collect { event -> handleEvent(event) }
        }
    }

    private fun handleEvent(event: MealViewModel.Event) = when (event) {
        is MealViewModel.Event.Meal -> {
            if (!event.mealList.isNullOrEmpty()) {
                if (MealViewModel.currentMeal == "조식" && event.mealList.size != 1) {
                    viewMeal(event.mealList[0])
                } else if (MealViewModel.currentMeal == "중식") {
                    viewMeal(if (event.mealList.size == 1) event.mealList[0] else event.mealList[1])
                } else if (MealViewModel.currentMeal == "석식" && event.mealList.size == 3) {
                    viewMeal(event.mealList[2])
                } else {
                    binding.mealTxt.text = "급식이 없습니다."
                }
            } else {
                binding.mealTxt.text = "급식이 없습니다."
            }
        }
        is MealViewModel.Event.MealDate -> {
            binding.dateTxt.text =
                "${event.date.slice(0..3)}년 ${event.date.slice(4..5)}월 ${event.date.slice(6..7)}일"
        }
        is MealViewModel.Event.MealTime -> {
            mealViewModel.meal()
        }
    }

    private fun viewMeal(item: MealEntity.MealItem?) = binding.apply {
        var content = ""
        item?.dishName?.forEach { data ->
            if (!data.equals('<') && !data.equals('>') && !data.equals('/') && !data.equals('b') && !data.equals(
                    'r'
                ) && !data.equals('(') && !data.equals(')')
                && !data.equals('0') && !data.equals('1') && !data.equals('2') && !data.equals(
                    '3'
                ) && !data.equals('4') && !data.equals('5')
                && !data.equals('6') && !data.equals('7') && !data.equals('8') && !data.equals(
                    '9'
                ) && !data.equals('.') && !data.equals('*')
            ) {
                content += data
            }
            if (data.equals('>')) {
                content += "\n"
            }
        }
        mealTxt.text = content
    }

    fun changeMonth(view: View) {
        if (view.id == R.id.nextBtn) {
            mealViewModel.setDate()
        } else {
            mealViewModel.setDate(false)
        }
    }
}