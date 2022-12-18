package com.example.school_meal.ui.component.meal

import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.school_meal.R
import com.example.school_meal.databinding.FragmentMealMonthBinding
import com.example.school_meal.ui.adapter.MealAdapter
import com.example.school_meal.ui.component.base.BaseFragment
import com.example.school_meal.ui.extension.repeatOnStart
import com.example.school_meal.viewmodel.MealViewModel

class MealMonthFragment : BaseFragment<FragmentMealMonthBinding>(R.layout.fragment_meal_month) {
    private val mealViewModel by activityViewModels<MealViewModel>()
    private lateinit var adapter: MealAdapter
    override fun init() {
        binding.meal = this
        initList()
        mealViewModel.mealMonth()
        repeatOnStart {
            mealViewModel.eventFlow.collect { event -> handleEvent(event) }
        }
    }

    private fun handleEvent(event: MealViewModel.Event) = when(event) {
        is MealViewModel.Event.Meal -> {
            adapter.submitList(event.mealList)
        }
        is MealViewModel.Event.MealDate -> {
            binding.monthTxt.text = "${event.date.slice(0..3)}년 ${event.date.slice(4..5)}월"
            mealViewModel.mealMonth()
        }
        is MealViewModel.Event.MealTime -> {
            mealViewModel.mealMonth()
        }
    }

    private fun initList() {
        adapter = MealAdapter()
        binding.mealList.apply {
            adapter = this@MealMonthFragment.adapter
            layoutManager = GridLayoutManager(context, 5)
        }
    }

    fun changeMonth(view: View) {
        if (view.id == R.id.nextBtn) {
            mealViewModel.setMonth()
        } else {
            mealViewModel.setMonth(false)
        }
    }
}