package com.example.school_meal.ui.component.time

import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.school_meal.R
import com.example.school_meal.databinding.FragmentTimeBinding
import com.example.school_meal.ui.adapter.TimeAdapter
import com.example.school_meal.ui.component.base.BaseFragment
import com.example.school_meal.ui.extension.repeatOnStart
import com.example.school_meal.viewmodel.TimeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TimeFragment : BaseFragment<FragmentTimeBinding>(R.layout.fragment_time) {
    private val timeViewModel by activityViewModels<TimeViewModel>()
    private lateinit var adapter: TimeAdapter

    override fun init() {
        timeViewModel.time()
        initList()
        repeatOnStart {
            timeViewModel.eventFlow.collect { event -> handleEvent(event) }
        }
    }

    private fun handleEvent(event: TimeViewModel.Event) = when (event) {
        is TimeViewModel.Event.Time -> {
            adapter.submitList(event.timeList)
        }
    }

    private fun initList() {
        adapter = TimeAdapter()
        binding.timeTableRecyclerview.apply {
            adapter = this@TimeFragment.adapter
            layoutManager = GridLayoutManager(context, 5)
        }
    }
}