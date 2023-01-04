package com.example.school_meal.ui.component.meal

import android.view.View
import androidx.fragment.app.activityViewModels
import com.example.school_meal.R
import com.example.school_meal.databinding.FragmentMealBinding
import com.example.school_meal.ui.component.base.BaseFragment
import com.example.school_meal.viewmodel.MealViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@AndroidEntryPoint
class MealFragment : BaseFragment<FragmentMealBinding>(R.layout.fragment_meal) {
    private val mealViewModel by activityViewModels<MealViewModel>()

    override fun init() {
        binding.meal = this
        initView()
    }

    private fun initView() = binding.apply {
        dayTxt.text =
            LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")).slice(6..7)
        mealViewModel.setMealType(
            when (LocalDateTime.now().hour) {
                in 0..9 -> "조식"
                in 9..13 -> "중식"
                else -> "석식"
            }
        )
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.mealContainer, MealDayFragment()).commit()
    }

    fun initTabBar(view: View) {
        val mealType = when (view.id) {
            R.id.breakfast -> "조식"
            R.id.lunch -> "중식"
            R.id.dinner -> "석식"
            else -> "조식"
        }
        mealViewModel.setMealType(
            mealType
        )
    }

    fun initDayBar(view: View) {
        when (view.id) {
            R.id.dayBtn -> {
                if (MealViewModel.viewType) {
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.mealContainer, MealDayFragment()).commit()
                    mealViewModel.changeViewType()
                }
            }
            R.id.monthBtn -> {
                if (!MealViewModel.viewType) {
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.mealContainer, MealMonthFragment()).commit()
                    mealViewModel.changeViewType()
                }
            }
        }
    }
}