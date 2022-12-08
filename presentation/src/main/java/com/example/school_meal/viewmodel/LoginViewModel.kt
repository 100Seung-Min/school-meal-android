package com.example.school_meal.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.param.LoginParam
import com.example.domain.usecase.auth.LoginUseCase
import com.example.domain.usecase.auth.SaveIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val saveIdUseCase: SaveIdUseCase
): ViewModel() {
    private val _loginState = MutableLiveData<Boolean>()
    val loginState: LiveData<Boolean> get() = _loginState
    fun login(id: String, pw: String) = viewModelScope.launch {
        kotlin.runCatching {
            loginUseCase.execute(LoginParam(id, pw))
        }.onSuccess {
            if (it) {
                saveIdUseCase.execute(id)
            }
            _loginState.value = it
        }
    }
}