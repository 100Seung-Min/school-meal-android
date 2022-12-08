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
import javax.inject.Inject

@HiltViewModel
class MealViewModel @Inject constructor(
    private val schoolMealUseCase: SchoolMealUseCase
) : ViewModel() {
    private val _mealInfo = SingleLiveEvent<List<MealEntity.MealItem>>()
    val mealInfo: MutableLiveData<List<MealEntity.MealItem>> get() = _mealInfo
    private val _currentMeal = SingleLiveEvent<String>()
    val currentMeal: MutableLiveData<String> get() = _currentMeal

    init {
        _currentMeal.value = "조식"
    }

    fun meal(day: String) = viewModelScope.launch {
        kotlin.runCatching {
            schoolMealUseCase.execute(day)
        }.onSuccess {
            _mealInfo.value = it?.row
        }.onFailure {

        }
    }

    fun setMealType(type: String) {
        if (_currentMeal.value != type) {
            _currentMeal.value = type
        }
    }
}