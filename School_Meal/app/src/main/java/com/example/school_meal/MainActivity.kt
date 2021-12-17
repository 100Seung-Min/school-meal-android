package com.example.school_meal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.school_meal.Adapter.SearchMealAdapter
import com.example.school_meal.DTO.SchoolMealData
import com.example.school_meal.DTO.dietRow
import com.example.school_meal.Fragment.MealFragment
import com.example.school_meal.Fragment.TimeTableFragment
import com.example.school_meal.databinding.ActivityMainBinding
import com.example.school_meal.retrofit.SchoolAPIClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    val binding by lazy {ActivityMainBinding.inflate(layoutInflater)}
    var mySchoolCode: String? = null
    var mySchoolName: String? = null
    var mySchoolNum: String? = null
    var mySchoolClass: String? = null
    var mySchoolGrade: String? = null
    var mySchoolLevel: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        mySchoolName = intent.getStringExtra("mySchoolName")
        mySchoolCode = intent.getStringExtra("mySchoolCode")
        mySchoolNum = intent.getStringExtra("mySchoolNum")
        mySchoolClass = intent.getStringExtra("mySchoolClass")
        mySchoolGrade = intent.getStringExtra("mySchoolGrade")
        mySchoolLevel = intent.getStringExtra("mySchoolLevel")

        setSupportActionBar(binding.toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24)
        supportActionBar!!.title = mySchoolName

        supportFragmentManager.beginTransaction().add(R.id.frame_layout, MealFragment(mySchoolCode!!, mySchoolNum!!)).commit()

        binding.bottomNavigation.itemIconTintList = null
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.school_date_list_menu -> {

                }
                R.id.school_meal_menu -> {
                    supportFragmentManager.beginTransaction().replace(R.id.frame_layout, MealFragment(mySchoolCode!!, mySchoolNum!!)).commit()
                }
                R.id.school_time_menu -> {
                    supportFragmentManager.beginTransaction().replace(R.id.frame_layout, TimeTableFragment(mySchoolCode!!, mySchoolNum!!, mySchoolClass!!, mySchoolGrade!!, mySchoolLevel!!)).commit()
                }
                else -> {
                    return@setOnItemSelectedListener false
                }
            }
            return@setOnItemSelectedListener true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> {
                val intent = Intent(this, SearchSchool::class.java)
                val edit = getSharedPreferences("MY_SCHOOL", MODE_PRIVATE).edit()
                edit.putString("mySchoolName", null)
                edit.putString("mySchoolCode", null)
                edit.putString("mySchoolNum", null)
                edit.putString("mySchoolClass", null)
                edit.putString("mySchoolGrade", null)
                edit.putString("mySchoolLevel", null)
                edit.commit()
                startActivity(intent)
                finish()
            }
        }
        return true
    }
}