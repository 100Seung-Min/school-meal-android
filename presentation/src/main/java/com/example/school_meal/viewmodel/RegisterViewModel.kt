package com.example.school_meal.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.SchoolInfoEntity
import com.example.domain.param.SignUpParam
import com.example.domain.usecase.auth.SendMsgUseCase
import com.example.domain.usecase.auth.SignUpUseCase
import com.example.domain.usecase.school.SchoolInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val schoolInfoUseCase: SchoolInfoUseCase,
    private val sendMsgUseCase: SendMsgUseCase,
    private val signUpUseCase: SignUpUseCase
) : ViewModel() {
    private val _schoolInfo = MutableLiveData<SchoolInfoEntity>()
    val schoolInfo: LiveData<SchoolInfoEntity> get() = _schoolInfo
    private val _certifyNum = MutableLiveData<String>()
    val certifyNum: LiveData<String> get() = _certifyNum

    private val _currentSchool = MutableLiveData<SchoolInfoEntity.SchoolInfo>()
    val currentSchool: LiveData<SchoolInfoEntity.SchoolInfo> get() = _currentSchool
    fun schoolInfo(schoolName: String) = viewModelScope.launch {
        kotlin.runCatching {
            schoolInfoUseCase.execute(schoolName)
        }.onSuccess {
            _schoolInfo.value = it
        }
    }

    fun setCurrentSchool(currentSchool: SchoolInfoEntity.SchoolInfo) {
        _currentSchool.value = currentSchool
    }

    fun sendMsg(phone: String) = viewModelScope.launch {
        kotlin.runCatching {
            sendMsgUseCase.execute(phone)
        }.onSuccess {
            _certifyNum.value = it
            Log.d("안녕", "sedMsg: $it")
        }
    }

    fun signUp(
        id: String,
        pw: String,
        phone: String,
        `class`: String,
        grade: String,
        name: String
    ) = viewModelScope.launch {
        kotlin.runCatching {
            signUpUseCase.execute(
                SignUpParam(
                    id,
                    pw,
                    phone,
                    currentSchool.value!!.cityCode,
                    currentSchool.value!!.schoolName,
                    currentSchool.value!!.schoolCode,
                    `class`,
                    grade,
                    name
                )
            )
        }.onSuccess {

        }.onFailure {

        }
    }
}