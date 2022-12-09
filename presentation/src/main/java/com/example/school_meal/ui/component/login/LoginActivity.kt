package com.example.school_meal.ui.component.login

import android.content.Intent
import android.view.View
import androidx.activity.viewModels
import com.example.school_meal.R
import com.example.school_meal.databinding.ActivityLoginBinding
import com.example.school_meal.ui.component.base.BaseActivity
import com.example.school_meal.ui.component.main.MainActivity
import com.example.school_meal.ui.component.register.RegisterActivity
import com.example.school_meal.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity: BaseActivity<ActivityLoginBinding> (R.layout.activity_login) {
    private val loginViewModel by viewModels<LoginViewModel>()
    override fun init() {
        loginViewModel.isLogin()
    }

    override fun observe() {
        observeLogin()
    }

    private fun observeLogin() {
        loginViewModel.loginState.observe(this) {
            if (it) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }
    }

    fun click(view: View) {
        when(view.id) {
            R.id.loginBtn -> loginViewModel.login(binding.writeId.text.toString(), binding.writePw.text.toString())
            R.id.registerBtn -> {
                startActivity(Intent(this, RegisterActivity::class.java))
            }
        }
    }
}