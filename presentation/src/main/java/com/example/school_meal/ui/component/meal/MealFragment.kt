package com.example.school_meal.ui.component.meal

import android.content.SharedPreferences
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.example.school_meal.R
import com.example.school_meal.databinding.FragmentMealBinding
import com.example.school_meal.ui.adapter.MealAdapter
import com.example.school_meal.ui.component.base.BaseFragment
import com.example.school_meal.ui.extension.onTabSelected
import com.example.school_meal.viewmodel.MealViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MealFragment : BaseFragment<FragmentMealBinding>(R.layout.fragment_meal) {
    private val mealViewModel by activityViewModels<MealViewModel>()
    lateinit var pref: SharedPreferences
    lateinit var mealAdapter: MealAdapter

    override fun init() {
        pref = requireContext().getSharedPreferences("MY_SCHOOL", AppCompatActivity.MODE_PRIVATE)
        mealViewModel.meal(pref.getString("mySchoolCode", "")!!, pref.getString("mySchoolNum", "")!!, "1")
        search()
        mealInfoObserve()
    }

    private fun search() = binding.mealTablayout.onTabSelected {
        mealViewModel.meal(pref.getString("mySchoolCode", "")!!, pref.getString("mySchoolNum", "")!!, "${it!!.position+1}")
    }

    private fun mealInfoObserve() {
        mealViewModel.mealInfo.observe(this) {
            binding.resultMealRecyclerview.onFlingListener = null
            setRecyclerView()
        }
    }

    private fun setRecyclerView() {
        mealAdapter = MealAdapter(mealViewModel.mealInfo.value)
        with(binding.resultMealRecyclerview) {
            adapter = mealAdapter
            layoutManager = GridLayoutManager(context, 5)
        }
        PagerSnapHelper().attachToRecyclerView(binding.resultMealRecyclerview)
    }
}