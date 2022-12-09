package com.example.school_meal.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.MealEntity
import com.example.domain.usecase.school.SchoolMealUseCase
import com.example.school_meal.ui.extension.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MealViewModel @Inject constructor(
    private val schoolMealUseCase: SchoolMealUseCase
) : ViewModel() {
    private val _mealInfo = SingleLiveEvent<List<MealEntity.MealItem>>()
    val mealInfo: MutableLiveData<List<MealEntity.MealItem>> get() = _mealInfo
    private val _currentMeal = SingleLiveEvent<String>()
    val currentMeal: MutableLiveData<String> get() = _currentMeal
    private val _currentMonth = SingleLiveEvent<String>()
    val currentMonth: MutableLiveData<String> get() = _currentMonth

    init {
        _currentMeal.value = "조식"
        _currentMonth.value = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"))
    }

    fun meal() = viewModelScope.launch {
        kotlin.runCatching {
            schoolMealUseCase.execute(_currentMonth.value!!.slice(0..5))
        }.onSuccess {
            _mealInfo.value = it?.row
        }.onFailure {

        }
    }

    fun setMonth(isPlus: Boolean = true) {
        val formatter = DateTimeFormatter.ofPattern("yyyyMMdd")
        var date = LocalDate.parse(_currentMonth.value, formatter)
        date = if (isPlus) {
            date.plusMonths(1)
        } else {
            date.minusMonths(1)
        }
        _currentMonth.value = date.format(formatter)
    }

    fun setMealType(type: String) {
        if (_currentMeal.value != type) {
            _currentMeal.value = type
        }
    }
}