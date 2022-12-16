package com.example.school_meal.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.SchoolInfoEntity
import com.example.domain.param.SignUpParam
import com.example.domain.usecase.auth.SendMsgUseCase
import com.example.domain.usecase.auth.SignUpUseCase
import com.example.domain.usecase.school.SchoolInfoUseCase
import com.example.school_meal.ui.extension.MutableEventFlow
import com.example.school_meal.ui.extension.asEventFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val schoolInfoUseCase: SchoolInfoUseCase,
    private val sendMsgUseCase: SendMsgUseCase,
    private val signUpUseCase: SignUpUseCase
) : ViewModel() {

    private val _eventFlow = MutableEventFlow<Event>()
    val eventFlow = _eventFlow.asEventFlow()

    companion object {
        var code: String? = null
        var currentSchool: SchoolInfoEntity.SchoolInfo? = null
    }

    fun schoolInfo(schoolName: String) = viewModelScope.launch {
        kotlin.runCatching {
            schoolInfoUseCase.execute(schoolName)
        }.onSuccess {
            event(Event.School(it))
        }
    }

    fun setCurrentSchool(currentSchool: SchoolInfoEntity.SchoolInfo) {
        event(Event.CurrentSchool(currentSchool))
    }

    fun sendMsg(phone: String) = viewModelScope.launch {
        kotlin.runCatching {
            sendMsgUseCase.execute(phone)
        }.onSuccess {
            event(Event.SendSuccess(it))
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
                    currentSchool!!.cityCode,
                    currentSchool!!.schoolName,
                    currentSchool!!.schoolCode,
                    `class`,
                    grade,
                    name,
                    currentSchool!!.schoolClass
                )
            )
        }.onSuccess {
            event(Event.RegisterSuccess(true))
        }.onFailure {

        }
    }

    private fun event(event: Event) = viewModelScope.launch {
        _eventFlow.emit(event)
    }

    sealed class Event {
        data class School(val schoolInfo: SchoolInfoEntity?) : Event()
        data class CurrentSchool(val school: SchoolInfoEntity.SchoolInfo): Event()
        data class SendSuccess(val num: String) : Event()
        data class RegisterSuccess(val status: Boolean) : Event()
    }
}