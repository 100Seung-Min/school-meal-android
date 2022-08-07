package com.example.school_meal.ui.component.search

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.example.data.remote.response.SchoolInfoData
import com.example.data.remote.response.infoRow
import com.example.school_meal.R
import com.example.school_meal.databinding.ActivitySearchBinding
import com.example.school_meal.ui.MainActivity
import com.example.school_meal.ui.adapter.SearchSchoolAdapter
import com.example.school_meal.ui.component.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@AndroidEntryPoint
class SearchActivity : BaseActivity<ActivitySearchBinding>(R.layout.activity_search) {

    val itemlist: ArrayList<infoRow> = ArrayList()
    lateinit var adapter: SearchSchoolAdapter
    var selectItem: infoRow = infoRow("", "", "", "")
    override fun init() {
        var pref = getSharedPreferences("MY_SCHOOL", MODE_PRIVATE)
        var edit = pref.edit()
        var mySchoolName = pref.getString("mySchoolName", null)
        var mySchoolCode = pref.getString("mySchoolCode", null)
        var mySchoolNum = pref.getString("mySchoolNum", null)
        var mySchoolClass = pref.getString("mySchoolClass", null)
        var mySchoolGrade = pref.getString("mySchoolGrade", null)
        var mySchoolLevel = pref.getString("mySchoolLevel", null)

        if(!mySchoolName.isNullOrEmpty()){
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("mySchoolName", mySchoolName)
            intent.putExtra("mySchoolCode", mySchoolCode)
            intent.putExtra("mySchoolNum", mySchoolNum)
            intent.putExtra("mySchoolClass", mySchoolClass)
            intent.putExtra("mySchoolGrade", mySchoolGrade)
            intent.putExtra("mySchoolLevel", mySchoolLevel)
            startActivity(intent)
            finish()
        }

        adapter = SearchSchoolAdapter(itemlist, this){
            selectItem = it
            binding.setSchoolBtn.visibility = View.VISIBLE
        }

        binding.setSchoolBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            mySchoolName = selectItem.SCHUL_NM
            mySchoolCode = selectItem.ATPT_OFCDC_SC_CODE
            mySchoolNum = selectItem.SD_SCHUL_CODE
            mySchoolLevel = selectItem.SCHUL_KND_SC_NM
            mySchoolClass = binding.classEdit.text.toString()
            mySchoolGrade = binding.gradeEdit.text.toString()
            edit.putString("mySchoolName", mySchoolName)
            edit.putString("mySchoolCode", mySchoolCode)
            edit.putString("mySchoolNum", mySchoolNum)
            edit.putString("mySchoolClass", mySchoolClass)
            edit.putString("mySchoolGrade", mySchoolGrade)
            edit.putString("mySchoolLevel", mySchoolLevel)
            edit.commit()
            intent.putExtra("mySchoolName", mySchoolName)
            intent.putExtra("mySchoolCode", mySchoolCode)
            intent.putExtra("mySchoolNum", mySchoolNum)
            intent.putExtra("mySchoolClass", mySchoolClass)
            intent.putExtra("mySchoolGrade", mySchoolGrade)
            intent.putExtra("mySchoolLevel", mySchoolLevel)
            startActivity(intent)
            finish()
        }

        binding.resultSchoolRecyclerview.adapter = adapter
        binding.resultSchoolRecyclerview.layoutManager = LinearLayoutManager(this)
        PagerSnapHelper().attachToRecyclerView(binding.resultSchoolRecyclerview)

        binding.searchSchoolBtn.setOnClickListener {
            val schoolName = binding.searchSchoolEdit.text.toString()
            if(binding.classEdit.text.isEmpty() && binding.gradeEdit.text.isEmpty() && schoolName.isEmpty()){
                ("모두 입력해주세요")
            }
            else if(schoolName.isEmpty()){
                makeToast("학교를 입력해주세요")
            }
            else if(binding.gradeEdit.text.isEmpty()){
                makeToast("학년을 입력해주세요")
            }
            else if(binding.classEdit.text.isEmpty()){
                makeToast("반을 입력해주세요")
            }
            else{
                SchoolAPIClient.API.getSchoolInfo(schoolName = schoolName).enqueue(object : Callback<SchoolInfoData>{
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

    override fun observe() {
    }

}