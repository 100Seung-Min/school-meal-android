package com.example.school_meal.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.school_meal.DTO.SchoolTimeDate
import com.example.school_meal.DTO.timeDateRow
import com.example.school_meal.R
import com.example.school_meal.databinding.FragmentTimeTableBinding
import com.example.school_meal.retrofit.SchoolAPIClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class TimeTableFragment(val mySchoolCode: String, val mySchoolNum: String, val mySchoolClass: String, val mySchoolGrade: String) : Fragment() {

    lateinit var binding: FragmentTimeTableBinding
    val itemlist: ArrayList<timeDateRow> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTimeTableBinding.inflate(inflater, container, false)
        val dayOfWeek = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)
        val startDay = SimpleDateFormat("yyyyMMdd").format(Date()).toInt() - (1 - dayOfWeek)
        val endDay = SimpleDateFormat("yyyyMMdd").format(Date()).toInt() + (7 - dayOfWeek)
        SchoolAPIClient.api.getHisTime(cityCode = mySchoolCode, schoolCode = mySchoolNum, grade = mySchoolGrade, className = mySchoolClass, startDay = startDay.toString(), endDay = endDay.toString()).enqueue(object : Callback<SchoolTimeDate>{
            override fun onResponse(
                call: Call<SchoolTimeDate>,
                response: Response<SchoolTimeDate>
            ) {
                println("여기 ${response.body()}")
                if(response.body()!!.hisTimetable != null){
                    for(timeData in response.body()!!.hisTimetable){
                        if(timeData.row != null){
                            for(data in timeData.row){
                                if(!data.ITRT_CNTNT.equals("토요휴업일")){
                                    itemlist.add(data)
                                }
                            }
                        }
                    }
                }
                println("여기 ${itemlist}")
            }

            override fun onFailure(call: Call<SchoolTimeDate>, t: Throwable) {
                println("여기 ${t}")
            }
        })
        return binding.root
    }
}