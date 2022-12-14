package com.example.school_meal.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TimeViewModel @Inject constructor(
): ViewModel() {
//    private val _timeInfo = MutableLiveData<List<TimeEntity.TimeDateRow>>()
//    val timeInfo: LiveData<List<TimeEntity.TimeDateRow>> get() = _timeInfo

    fun time(cityCode: String, className: String, schoolCode: String, endDay: String, startDay: String, grade: String, type: String) = viewModelScope.launch {
//        _timeInfo.value = when(type) {
//            "고등학교" -> {}
//            "중학교" -> {}
//            "초등학교" ->{}
//            else -> listOf()
//        }
    }
}