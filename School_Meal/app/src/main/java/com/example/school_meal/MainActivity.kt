package com.example.school_meal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.school_meal.DTO.SchoolMealData
import com.example.school_meal.databinding.ActivityMainBinding
import com.example.school_meal.retrofit.SchoolAPIClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    val binding by lazy {ActivityMainBinding.inflate(layoutInflater)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        SchoolAPIClient.api.getMeal().enqueue(object : Callback<SchoolMealData>{
            override fun onResponse(call: Call<SchoolMealData>, response: Response<SchoolMealData>) {
                println("여기 ${response.body()}")
            }

            override fun onFailure(call: Call<SchoolMealData>, t: Throwable) {
                println("여기 ${t}")
            }
        })
    }
}