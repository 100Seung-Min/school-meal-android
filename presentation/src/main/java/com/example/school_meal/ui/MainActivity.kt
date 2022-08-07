package com.example.school_meal.ui

import com.example.school_meal.R
import com.example.school_meal.ui.component.base.BaseActivity
import com.example.school_meal.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    var mySchoolCode: String? = null
    var mySchoolName: String? = null
    var mySchoolNum: String? = null
    var mySchoolClass: String? = null
    var mySchoolGrade: String? = null
    var mySchoolLevel: String? = null

    override fun viewSetting() {
        getData()
        settingAppBar()
        settingBottomNav()
    }

    override fun observeEvent() {
    }

    private fun getData(){
        mySchoolName = intent.getStringExtra("mySchoolName")
        mySchoolCode = intent.getStringExtra("mySchoolCode")
        mySchoolNum = intent.getStringExtra("mySchoolNum")
        mySchoolClass = intent.getStringExtra("mySchoolClass")
        mySchoolGrade = intent.getStringExtra("mySchoolGrade")
        mySchoolLevel = intent.getStringExtra("mySchoolLevel")
    }

    private fun settingAppBar(){
        setSupportActionBar(binding.toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24)
        supportActionBar!!.title = mySchoolName
    }

    private fun settingBottomNav(){

    }
}