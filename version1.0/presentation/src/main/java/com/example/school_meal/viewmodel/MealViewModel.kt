package com.example.school_meal.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.MealEntity
import com.example.domain.usecase.school.SchoolMealUseCase
import com.example.school_meal.ui.extension.MutableEventFlow
import com.example.school_meal.ui.extension.asEventFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
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
        var viewType = false
    }

    fun meal() = viewModelScope.launch {
        kotlin.runCatching {
            schoolMealUseCase.execute(currentMonth)
        }.onSuccess {
            event(Event.Meal(it?.row))
            event(Event.MealDate(currentMonth))
        }
    }

    fun mealMonth() = viewModelScope.launch {
        kotlin.runCatching {
            schoolMealUseCase.execute(currentMonth.slice(0..5))
        }.onSuccess {
            if (it != null && !it.row.isNullOrEmpty()) {
                var list = when (getDate(it.row[0].mealDay)) {
                    "월" -> listOf()
                    "화" -> addList(1)
                    "수" -> addList(2)
                    "목" -> addList(3)
                    "금" -> addList(4)
                    else -> listOf()
                }
                it.row.forEach {
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
                event(Event.Meal(list))
            } else {
                event(Event.Meal(null))
            }
            event(Event.MealDate(currentMonth))
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
        mealMonth()
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
        meal()
        event(Event.MealDate(currentMonth))
    }

    fun setMealType(type: String) {
        if (currentMeal != type) {
            currentMeal = type
            event(Event.MealTime(type))
        }
    }

    fun changeViewType() {
        viewType = !viewType
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

    private fun event(event: Event) = viewModelScope.launch {
        _eventFlow.emit(event)
    }

    sealed class Event {
        data class Meal(val mealList: List<MealEntity.MealItem>?) : Event()
        data class MealTime(val time: String): Event()
        data class MealDate(val date: String): Event()
    }
}