package com.example.school_meal.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.SchoolEntity
import com.example.domain.usecase.SearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchUseCase: SearchUseCase
): ViewModel() {
    private val _schoolInfo = MutableLiveData<List<SchoolEntity.SchoolInfo.InfoRow>>()
    val schoolInfo: LiveData<List<SchoolEntity.SchoolInfo.InfoRow>> get() = _schoolInfo

    fun search(schoolName: String) {
        try {
            viewModelScope.launch {
                _schoolInfo.value = searchUseCase.execute(schoolName).schoolInfo.get(1)?.row!!
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}