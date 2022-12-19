package com.example.school_meal.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.TimeEntity
import com.example.domain.usecase.school.SchoolTimeUseCase
import com.example.school_meal.ui.extension.MutableEventFlow
import com.example.school_meal.ui.extension.asEventFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TimeViewModel @Inject constructor(
    private val schoolTimeUseCase: SchoolTimeUseCase
) : ViewModel() {

    private val _eventFlow = MutableEventFlow<Event>()
    val eventFlow = _eventFlow.asEventFlow()

    fun time() = viewModelScope.launch {
        kotlin.runCatching {
            schoolTimeUseCase.execute()
        }.onSuccess {
            var listWeek = listOf<List<TimeEntity>?>()
            var listDay = listOf<TimeEntity>()
            it?.forEach { timeEntity ->
                listDay = listDay.plus(timeEntity)
                if (timeEntity.time == "7") {
                    listWeek = listWeek.plus(listOf(listDay))
                    listDay = listOf()
                }
            }
            event(Event.Time(listWeek))
        }
    }

    private fun event(event: Event) = viewModelScope.launch {
        _eventFlow.emit(event)
    }

    sealed class Event {
        data class Time(val timeList: List<List<TimeEntity>?>) : Event()
    }
}