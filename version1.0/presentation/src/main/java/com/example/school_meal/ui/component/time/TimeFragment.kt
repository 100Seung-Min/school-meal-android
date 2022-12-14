package com.example.school_meal.ui.component.time

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.school_meal.R
import com.example.school_meal.databinding.FragmentTimeBinding
import com.example.school_meal.ui.component.base.BaseFragment
import com.example.school_meal.viewmodel.TimeViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class TimeFragment : BaseFragment<FragmentTimeBinding>(R.layout.fragment_time) {
    private val timeViewModel by activityViewModels<TimeViewModel>()

    private val dayOfWeek = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)
    private val startDay = SimpleDateFormat("yyyyMMdd").format(Date()).toInt() + (1 - dayOfWeek)
    private val endDay = SimpleDateFormat("yyyyMMdd").format(Date()).toInt() + (7 - dayOfWeek)

    override fun init() {
        time()
        timeInfoObserve()
    }

    private fun time() {
        val pref =
            requireContext().getSharedPreferences("MY_SCHOOL", AppCompatActivity.MODE_PRIVATE)
        timeViewModel.time(
            pref.getString("mySchoolCode", "")!!,
            pref.getString("mySchoolClass", "")!!,
            pref.getString("mySchoolNum", "")!!,
            endDay.toString(),
            startDay.toString(),
            pref.getString("mySchoolGrade", "")!!,
            pref.getString("mySchoolLevel", "")!!
        )
    }

    private fun timeInfoObserve() {
//        timeViewModel.timeInfo.observe(this) {
//            adapter = TimeAdapter(it)
//            binding.timeTableRecyclerview.adapter = adapter
//            binding.timeTableRecyclerview.layoutManager = LinearLayoutManager(context)
//        }
    }
}