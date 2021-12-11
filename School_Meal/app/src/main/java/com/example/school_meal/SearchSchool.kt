package com.example.school_meal

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.SnapHelper
import com.example.school_meal.Adapter.SearchSchoolAdapter
import com.example.school_meal.DTO.SchoolInfoData
import com.example.school_meal.DTO.infoRow
import com.example.school_meal.databinding.ActivitySearchSchoolBinding
import com.example.school_meal.retrofit.SchoolAPIClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchSchool : AppCompatActivity() {

    val binding by lazy { ActivitySearchSchoolBinding.inflate(layoutInflater) }
    val itemlist: ArrayList<infoRow> = ArrayList()
    lateinit var adapter: SearchSchoolAdapter
    var selectItem: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        adapter = SearchSchoolAdapter(itemlist, this){
            selectItem = it.SCHUL_NM
        }

        binding.resultSchoolRecyclerview.adapter = adapter
        binding.resultSchoolRecyclerview.layoutManager = LinearLayoutManager(this)
        PagerSnapHelper().attachToRecyclerView(binding.resultSchoolRecyclerview)

        binding.searchSchoolBtn.setOnClickListener {
            val schoolName = binding.searchSchoolEdit.text.toString()
            if(schoolName.isEmpty()){
                makeToast("학교를 입력해주세요")
            }
            else{
                SchoolAPIClient.api.getSchoolInfo(schoolName = schoolName).enqueue(object : Callback<SchoolInfoData>{
                    override fun onResponse(
                        call: Call<SchoolInfoData>,
                        response: Response<SchoolInfoData>
                    ) {
                        itemlist.clear()
                        if(response.body()!!.schoolInfo != null){
                            for(infoData in response.body()!!.schoolInfo){
                                if(infoData.row != null){
                                    for(data in infoData.row){
                                        itemlist.add(data)
                                    }
                                }
                            }
                        }
                        else{
                            makeToast("검색결과가 없습니다")
                        }
                        adapter.notifyDataSetChanged()
                    }

                    override fun onFailure(call: Call<SchoolInfoData>, t: Throwable) {
                        println("여기 ${t}")
                    }

                })
            }
        }
    }

    fun makeToast(text: String){
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}