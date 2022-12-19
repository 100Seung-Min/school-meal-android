package com.example.school_meal.ui.component.main

import android.content.Intent
import android.view.View
import androidx.activity.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.school_meal.R
import com.example.school_meal.ui.component.base.BaseActivity
import com.example.school_meal.databinding.ActivityMainBinding
import com.example.school_meal.ui.component.login.LoginActivity
import com.example.school_meal.ui.extension.repeatOnStart
import com.example.school_meal.viewmodel.ProfileViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val activityViewModel by viewModels<ProfileViewModel>()
    override fun init() {
        binding.main = this
        settingBottomNav()
        repeatOnStart {
            activityViewModel.eventFlow.collect {event -> handleEvent(event)}
        }
    }

    override fun observe() {
    }

    fun logout(view: View) {
        activityViewModel.logout()
    }

    private fun handleEvent(event: ProfileViewModel.Event) = when(event) {
        is ProfileViewModel.Event.Logout -> {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    private fun settingBottomNav(){
        val navController = supportFragmentManager.findFragmentById(R.id.frame_layout)?.findNavController()
        val nav = binding.bottomNavigation as BottomNavigationView
        navController?.let {
            nav.setupWithNavController(navController)
        }
    }
}