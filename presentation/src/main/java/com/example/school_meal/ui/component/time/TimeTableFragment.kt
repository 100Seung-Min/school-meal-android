package com.example.school_meal.ui.component.time

import androidx.recyclerview.widget.LinearLayoutManager
import com.example.data.remote.response.*
import com.example.school_meal.R
import com.example.school_meal.databinding.FragmentTimeTableBinding
import com.example.school_meal.ui.adapter.SearchTimeAdapter
import com.example.school_meal.ui.component.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class TimeTableFragment(
    val mySchoolCode: String,
    val mySchoolNum: String,
    val mySchoolClass: String,
    val mySchoolGrade: String,
    val mySchoolLevel: String,
) : BaseFragment<FragmentTimeTableBinding>(
    R.layout.fragment_time_table) {
    val itemlist: ArrayList<timeDateRow> = ArrayList()
    val timeTable: SchoolTimeDate = SchoolTimeDate(ArrayList(), ArrayList(), ArrayList())
    lateinit var adapter: SearchTimeAdapter

    override fun init() {
        adapter = SearchTimeAdapter(timeTable, mySchoolLevel)
        binding.timeTableRecyclerview.adapter = adapter
        binding.timeTableRecyclerview.layoutManager = LinearLayoutManager(context)

        val dayOfWeek = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)
        val startDay = SimpleDateFormat("yyyyMMdd").format(Date()).toInt() + (1 - dayOfWeek)
        val endDay = SimpleDateFormat("yyyyMMdd").format(Date()).toInt() + (7 - dayOfWeek)
        if(mySchoolLevel == "고등학교"){
            SchoolAPIClient.API.getHisTime(cityCode = mySchoolCode,
                schoolCode = mySchoolNum,
                grade = mySchoolGrade,
                className = mySchoolClass,
                startDay = startDay.toString(),
                endDay = endDay.toString()).enqueue(object : Callback<SchoolTimeDate> {
                override fun onResponse(
                    call: Call<SchoolTimeDate>,
                    response: Response<SchoolTimeDate>,
                ) {
                    if (response.body()!!.hisTimetable != null) {
                        for (timeData in response.body()!!.hisTimetable) {
                            if (timeData.row != null) {
                                for (data in timeData.row) {
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
            SchoolAPIClient.API.getMisTime(cityCode = mySchoolCode,
                schoolCode = mySchoolNum,
                grade = mySchoolGrade,
                className = mySchoolClass,
                startDay = startDay.toString(),
                endDay = endDay.toString()).enqueue(object : Callback<SchoolTimeDate> {
                override fun onResponse(
                    call: Call<SchoolTimeDate>,
                    response: Response<SchoolTimeDate>,
                ) {
                    if (response.body()!!.misTimetable != null) {
                        for (timeData in response.body()!!.misTimetable) {
                            if (timeData.row != null) {
                                for (data in timeData.row) {
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
            SchoolAPIClient.API.getElsTime(cityCode = mySchoolCode,
                schoolCode = mySchoolNum,
                grade = mySchoolGrade,
                className = mySchoolClass,
                startDay = startDay.toString(),
                endDay = endDay.toString()).enqueue(object : Callback<SchoolTimeDate> {
                override fun onResponse(
                    call: Call<SchoolTimeDate>,
                    response: Response<SchoolTimeDate>,
                ) {
                    println("여기 ${response}")
                    if (response.body()!!.elsTimetable != null) {
                        for (timeData in response.body()!!.elsTimetable) {
                            if (timeData.row != null) {
                                for (data in timeData.row) {
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
    }
}