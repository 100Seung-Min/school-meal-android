package com.example.school_meal.ui.component.search

import android.content.Intent
import android.content.SharedPreferences
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.example.school_meal.R
import com.example.school_meal.databinding.ActivitySearchBinding
import com.example.school_meal.ui.adapter.SearchSchoolAdapter
import com.example.school_meal.ui.component.base.BaseActivity
import com.example.school_meal.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import com.example.domain.entity.SchoolEntity.SchoolInfo.InfoRow
import com.example.school_meal.ui.MainActivity

@AndroidEntryPoint
class SearchActivity : BaseActivity<ActivitySearchBinding>(R.layout.activity_search) {
    private val searchViewModel by viewModels<SearchViewModel>()
    lateinit var searchAdapter: SearchSchoolAdapter
    lateinit var pref: SharedPreferences
    var selectItem: InfoRow = InfoRow("", "", "", "")

    override fun init() {
        binding.searchSchool = this
        pref = getSharedPreferences("MY_SCHOOL", MODE_PRIVATE)
        isSave()
    }

    override fun observe() {
        schoolInfoObserve()
    }

    fun search(view: View) = with(binding){
        if(searchSchoolEdit.text.isNullOrEmpty()) {

        } else if(gradeEdit.text.isNullOrEmpty()) {

        } else if(classEdit.text.isNullOrEmpty()) {

        } else {
            searchViewModel.search(searchSchoolEdit.text.toString())
        }
    }

    fun save(view: View) {
        var edit = pref.edit()
        val intent = Intent(this, MainActivity::class.java)
        edit.putString("mySchoolName", selectItem.schoolName)
        edit.putString("mySchoolCode", selectItem.cityCode)
        edit.putString("mySchoolNum", selectItem.cityCode)
        edit.putString("mySchoolClass", binding.classEdit.text.toString())
        edit.putString("mySchoolGrade", binding.gradeEdit.text.toString())
        edit.putString("mySchoolLevel", selectItem.schoolType)
        edit.commit()
        startActivity(intent)
        finish()
    }

    private fun isSave() {
        if(!pref.getString("mySchoolName", null).isNullOrEmpty()){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun setRecyclerView() {
        searchAdapter = SearchSchoolAdapter(searchViewModel.schoolInfo.value) {
            selectItem = it
            binding.setSchoolBtn.visibility = View.VISIBLE
        }
        with(binding.resultSchoolRecyclerview) {
            adapter = searchAdapter
            layoutManager = LinearLayoutManager(this@SearchActivity)
        }
        PagerSnapHelper().attachToRecyclerView(binding.resultSchoolRecyclerview)
    }

    private fun schoolInfoObserve() {
        searchViewModel.schoolInfo.observe(this) {
            binding.resultSchoolRecyclerview.onFlingListener = null
            setRecyclerView()
        }
    }
}