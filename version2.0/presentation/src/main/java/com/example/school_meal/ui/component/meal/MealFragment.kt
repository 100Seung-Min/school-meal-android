package com.example.school_meal.ui.component.meal

import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.school_meal.R
import com.example.school_meal.databinding.FragmentMealBinding
import com.example.school_meal.ui.component.base.BaseFragment
import com.example.school_meal.viewmodel.MealViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@AndroidEntryPoint
class MealFragment : BaseFragment<FragmentMealBinding>(R.layout.fragment_meal) {
    private val mealViewModel by activityViewModels<MealViewModel>()

    override fun init() {
        binding.meal = this
        viewFragment(MealMonthFragment())
        binding.dayTxt.text = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")).slice(6..7)
    }

    fun initTabBar(view: View) {
        val mealType = when (view.id) {
            R.id.breakfast -> "조식"
            R.id.lunch -> "중식"
            R.id.dinner -> "석식"
            else -> "조식"
        }
        if (mealType != mealViewModel.currentMeal.value) {
            mealViewModel.setMealType(
                mealType
            )
            viewFragment(MealMonthFragment())
        }
    }

    fun initDayBar(view: View) {

    }

    private fun viewFragment(fragment: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.mealContainer, fragment).commit()
    }
}