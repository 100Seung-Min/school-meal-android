package com.example.school_meal.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.param.LoginParam
import com.example.domain.usecase.auth.GetIdUseCase
import com.example.domain.usecase.auth.LoginUseCase
import com.example.domain.usecase.auth.SaveIdUseCase
import com.example.school_meal.ui.extension.MutableEventFlow
import com.example.school_meal.ui.extension.asEventFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val saveIdUseCase: SaveIdUseCase,
    private val getIdUseCase: GetIdUseCase
): ViewModel() {

    private val _eventFlow = MutableEventFlow<Event>()
    val eventFlow = _eventFlow.asEventFlow()

    fun login(id: String, pw: String) = viewModelScope.launch {
        kotlin.runCatching {
            loginUseCase.execute(LoginParam(id, pw))
        }.onSuccess {
            if (it) {
                saveIdUseCase.execute(id)
                event(Event.LoginSuccess)
            }
        }
    }

    fun isLogin() = viewModelScope.launch {
        val id = getIdUseCase.execute()
        if (!id.isNullOrBlank()) {
            event(Event.LoginSuccess)
        }
    }

    private fun event(event: Event) = viewModelScope.launch {
        _eventFlow.emit(event)
    }

    sealed class Event {
        object LoginSuccess: Event()
    }
}