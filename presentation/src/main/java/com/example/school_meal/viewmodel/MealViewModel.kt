package com.example.school_meal.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.MealEntity
import com.example.domain.usecase.MealUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealViewModel @Inject constructor(
    private val mealUseCase: MealUseCase
): ViewModel() {
    private val _mealInfo = MutableLiveData<List<MealEntity.MealServiceDietInfo.DietRow>>()
    val mealInfo: LiveData<List<MealEntity.MealServiceDietInfo.DietRow>> get() = _mealInfo

    fun meal(cityCode: String, schoolCode: String, mealType: String)  = viewModelScope.launch {
        mealUseCase.execute(cityCode, schoolCode, mealType).let { response ->
            if(response?.mealServiceDietInfo != null) {
                _mealInfo.value = response.mealServiceDietInfo?.get(1)?.row!!
            }
        }
    }
}