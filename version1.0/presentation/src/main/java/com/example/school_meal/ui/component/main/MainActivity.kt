package com.example.school_meal.ui.component.main

import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.school_meal.R
import com.example.school_meal.ui.component.base.BaseActivity
import com.example.school_meal.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    override fun init() {
        settingAppBar()
        settingBottomNav()
    }

    override fun observe() {
    }

    private fun settingAppBar(){
        setSupportActionBar(binding.toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24)
        supportActionBar!!.title = getSharedPreferences("MY_SCHOOL", MODE_PRIVATE).getString("mySchoolName", "")
    }

    private fun settingBottomNav(){
        val navController = supportFragmentManager.findFragmentById(R.id.frame_layout)?.findNavController()
        val nav = binding.bottomNavigation as BottomNavigationView
        navController?.let {
            nav.setupWithNavController(navController)
        }
    }
}