package com.example.school_meal.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.TimeEntity
import com.example.domain.usecase.ElsTimeUseCase
import com.example.domain.usecase.HisTimeUseCase
import com.example.domain.usecase.MisTimeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TimeViewModel @Inject constructor(
    private val hisTimeUseCase: HisTimeUseCase,
    private val misTimeUseCase: MisTimeUseCase,
    private val elsTimeUseCase: ElsTimeUseCase
): ViewModel() {
    private val _timeInfo = MutableLiveData<List<TimeEntity.TimeDateRow>>()
    val timeInfo: LiveData<List<TimeEntity.TimeDateRow>> get() = _timeInfo

    fun time(cityCode: String, className: String, schoolCode: String, endDay: String, startDay: String, grade: String, type: String) {
        try {
            viewModelScope.launch {
                _timeInfo.value = when(type) {
                    "고등학교" -> hisTimeUseCase.execute(cityCode, className, schoolCode, endDay, startDay, grade).hisTimetable.get(1)?.row!!
                    "중학교" -> misTimeUseCase.execute(cityCode, className, schoolCode, endDay, startDay, grade).misTimetable.get(1)?.row!!
                    "초등학교" -> elsTimeUseCase.execute(cityCode, className, schoolCode, endDay, startDay, grade).elsTimetable.get(1)?.row!!
                    else -> listOf()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}