package com.example.school_meal.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.MealEntity
import com.example.domain.usecase.school.SchoolMealUseCase
import com.example.school_meal.ui.extension.MutableEventFlow
import com.example.school_meal.ui.extension.asEventFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class MealViewModel @Inject constructor(
    private val schoolMealUseCase: SchoolMealUseCase
) : ViewModel() {

    private val _eventFlow = MutableEventFlow<Event>()
    val eventFlow = _eventFlow.asEventFlow()

    companion object {
        var currentMeal = "조식"
        var currentMonth = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"))
    }

    init {
        event(Event.MealDate(currentMonth))
    }

    fun meal() = viewModelScope.launch {
        kotlin.runCatching {
            schoolMealUseCase.execute(currentMonth)
        }.onSuccess {
            event(Event.Meal(it?.row))
        }
    }

    fun mealMonth() = viewModelScope.launch {
        kotlin.runCatching {
            schoolMealUseCase.execute(currentMonth.slice(0..5))
        }.onSuccess {
            event(Event.Meal(it?.row))
        }.onFailure {

        }
    }

    fun setMonth(isPlus: Boolean = true) {
        val formatter = DateTimeFormatter.ofPattern("yyyyMMdd")
        var date = LocalDate.parse(currentMonth, formatter)
        currentMonth = (if (isPlus) {
            date.plusMonths(1)
        } else {
            date.minusMonths(1)
        }).format(formatter)
        event(Event.MealDate(currentMonth))
    }

    fun setDate(isPlus: Boolean = true) {
        val formatter = DateTimeFormatter.ofPattern("yyyyMMdd")
        var date = LocalDate.parse(currentMonth, formatter)
        currentMonth = (if (isPlus) {
            date.plusDays(1)
        } else {
            date.minusDays(1)
        }).format(formatter)
        event(Event.MealDate(currentMonth))
    }

    fun setMealType(type: String) {
        if (currentMeal != type) {
            currentMeal = type
            event(Event.MealTime(type))
        }
    }

    private fun event(event: Event) = viewModelScope.launch {
        _eventFlow.emit(event)
    }

    sealed class Event {
        data class Meal(val mealList: List<MealEntity.MealItem>?) : Event()
        data class MealTime(val time: String): Event()
        data class MealDate(val date: String): Event()
    }
}