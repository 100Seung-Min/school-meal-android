package com.example.school_meal.ui.component.time

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.school_meal.data.DTO.*
import com.example.school_meal.databinding.FragmentTimeTableBinding
import com.example.school_meal.data.retrofit.SchoolAPIClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class TimeTableFragment(val mySchoolCode: String, val mySchoolNum: String, val mySchoolClass: String, val mySchoolGrade: String, val mySchoolLevel: String) : Fragment() {

    lateinit var binding: FragmentTimeTableBinding
    val itemlist: ArrayList<timeDateRow> = ArrayList()
    val timeTable: SchoolTimeDate = SchoolTimeDate(ArrayList(), ArrayList(), ArrayList())
    lateinit var adapter: SearchTimeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTimeTableBinding.inflate(inflater, container, false)

        adapter = SearchTimeAdapter(timeTable, mySchoolLevel)
        binding.timeTableRecyclerview.adapter = adapter
        binding.timeTableRecyclerview.layoutManager = LinearLayoutManager(context)

        val dayOfWeek = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)
        val startDay = SimpleDateFormat("yyyyMMdd").format(Date()).toInt() + (1 - dayOfWeek)
        val endDay = SimpleDateFormat("yyyyMMdd").format(Date()).toInt() + (7 - dayOfWeek)
        if(mySchoolLevel == "고등학교"){
            SchoolAPIClient.api.getHisTime(cityCode = mySchoolCode, schoolCode = mySchoolNum, grade = mySchoolGrade, className = mySchoolClass, startDay = startDay.toString(), endDay = endDay.toString()).enqueue(object : Callback<SchoolTimeDate>{
                override fun onResponse(
                    call: Call<SchoolTimeDate>,
                    response: Response<SchoolTimeDate>
                ) {
                    if(response.body()!!.hisTimetable != null){
                        for(timeData in response.body()!!.hisTimetable){
                            if(timeData.row != null){
                                for(data in timeData.row){
                                    itemlist.add(data)
                                }
                            }
                        }
                    }
                    val hisTimetable = hisTimetable(ArrayList())
                    for(data in itemlist){
                        val perio = data.PERIO
                        val cntnt = data.ITRT_CNTNT
                        if(data.PERIO.toInt() == 1 && !hisTimetable.row.isEmpty()){
                            val timeTableDay = hisTimetable(ArrayList())
                            for(dayTime in hisTimetable.row){
                                timeTableDay.row.add(dayTime)
                            }
                            if(timeTableDay.row.size != 1){
                                timeTable.hisTimetable.add(timeTableDay)
                                hisTimetable.row.clear()
                            }
                        }
                        if(perio.toInt() != hisTimetable.row.size){
                            hisTimetable.row.add(timeDateRow(perio, cntnt))
                        }
                    }
                    adapter.notifyDataSetChanged()
                }

                override fun onFailure(call: Call<SchoolTimeDate>, t: Throwable) {
                    println("여기 ${t}")
                }
            })
        }
        else if(mySchoolLevel == "중학교"){
            SchoolAPIClient.api.getMisTime(cityCode = mySchoolCode, schoolCode = mySchoolNum, grade = mySchoolGrade, className = mySchoolClass, startDay = startDay.toString(), endDay = endDay.toString()).enqueue(object : Callback<SchoolTimeDate>{
                override fun onResponse(
                    call: Call<SchoolTimeDate>,
                    response: Response<SchoolTimeDate>
                ) {
                    if(response.body()!!.misTimetable != null){
                        for(timeData in response.body()!!.misTimetable){
                            if(timeData.row != null){
                                for(data in timeData.row){
                                    itemlist.add(data)
                                }
                            }
                        }
                    }
                    val misTimetable = misTimetable(ArrayList())
                    for(data in itemlist){
                        val perio = data.PERIO
                        val cntnt = data.ITRT_CNTNT
                        if(data.PERIO.toInt() == 1 && !misTimetable.row.isEmpty()){
                            val timeTableDay = misTimetable(ArrayList())
                            for(dayTime in misTimetable.row){
                                timeTableDay.row.add(dayTime)
                            }
                            if(timeTableDay.row.size != 1){
                                timeTable.misTimetable.add(timeTableDay)
                                misTimetable.row.clear()
                            }
                        }
                        if(perio.toInt() != misTimetable.row.size){
                            misTimetable.row.add(timeDateRow(perio, cntnt))
                        }
                    }
                    adapter.notifyDataSetChanged()
                }

                override fun onFailure(call: Call<SchoolTimeDate>, t: Throwable) {
                    println("여기 ${t}")
                }
            })
        }
        else if(mySchoolLevel == "초등학교"){
            SchoolAPIClient.api.getElsTime(cityCode = mySchoolCode, schoolCode = mySchoolNum, grade = mySchoolGrade, className = mySchoolClass, startDay = startDay.toString(), endDay = endDay.toString()).enqueue(object : Callback<SchoolTimeDate>{
                override fun onResponse(
                    call: Call<SchoolTimeDate>,
                    response: Response<SchoolTimeDate>
                ) {
                    println("여기 ${response}")
                    if(response.body()!!.elsTimetable != null){
                        for(timeData in response.body()!!.elsTimetable){
                            if(timeData.row != null){
                                for(data in timeData.row){
                                    itemlist.add(data)
                                }
                            }
                        }
                    }
                    val elsTimetable = elsTimetable(ArrayList())
                    for(data in itemlist){
                        val perio = data.PERIO
                        val cntnt = data.ITRT_CNTNT
                        if(data.PERIO.toInt() == 1 && !elsTimetable.row.isEmpty()){
                            val timeTableDay = elsTimetable(ArrayList())
                            for(dayTime in elsTimetable.row){
                                timeTableDay.row.add(dayTime)
                            }
                            if(timeTableDay.row.size != 1){
                                timeTable.elsTimetable.add(timeTableDay)
                                elsTimetable.row.clear()
                            }
                        }
                        if(perio.toInt() != elsTimetable.row.size){
                            elsTimetable.row.add(timeDateRow(perio, cntnt))
                        }
                    }
                    adapter.notifyDataSetChanged()
                }

                override fun onFailure(call: Call<SchoolTimeDate>, t: Throwable) {
                    println("여기 ${t}")
                }
            })
        }
        return binding.root
    }
}