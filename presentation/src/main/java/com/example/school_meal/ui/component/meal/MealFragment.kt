package com.example.school_meal.ui.component.meal

import androidx.recyclerview.widget.GridLayoutManager
import com.example.data.remote.response.SchoolMealData
import com.example.data.remote.response.dietRow
import com.example.school_meal.R
import com.example.school_meal.databinding.FragmentMealBinding
import com.example.school_meal.ui.adapter.SearchMealAdapter
import com.example.school_meal.ui.component.base.BaseFragment
import com.google.android.material.tabs.TabLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat

class MealFragment(val mySchoolCode: String, val mySchoolNum: String) :
    BaseFragment<FragmentMealBinding>(R.layout.fragment_meal) {
    val itemlist: ArrayList<dietRow> = ArrayList()
    lateinit var adapter: SearchMealAdapter
    var mode = 0

    override fun init() {
        adapter = SearchMealAdapter(itemlist, context)
        binding.resultMealRecyclerview.adapter = adapter
        binding.resultMealRecyclerview.layoutManager = GridLayoutManager(context, 5)
//        PagerSnapHelper().attachToRecyclerView(binding.resultMealRecyclerview)

        binding.mealTablayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                mode = 0
                search_meal(tab!!.position + 1)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })

        search_meal(1)
    }

    fun search_meal(choose: Int){
        SchoolAPIClient.API.getMeal(cityCode = mySchoolCode!!, schoolCode = mySchoolNum!!)
            .enqueue(object :
                Callback<SchoolMealData> {
                override fun onResponse(
                    call: Call<SchoolMealData>,
                    response: Response<SchoolMealData>,
                ) {
                    itemlist.clear()
                    if (response.body()!!.mealServiceDietInfo != null) {
                        for (dietData in response.body()!!.mealServiceDietInfo) {
                            if (dietData.row != null) {
                                for (data in dietData.row) {
                                    val date = SimpleDateFormat("yyyyMMdd").parse(data.MLSV_YMD)
                                    if (SimpleDateFormat("EE").format(date)
                                            .equals("화") && mode == 0
                                    ) {
                                        addList(1)
                                }
                                if(SimpleDateFormat("EE").format(date).equals("수") && mode == 0){
                                    addList(2)
                                }
                                if(SimpleDateFormat("EE").format(date).equals("목") && mode == 0){
                                    addList(3)
                                }
                                if(SimpleDateFormat("EE").format(date).equals("금") && mode == 0){
                                    addList(4)
                                }
                                if(choose == 1 && data.MMEAL_SC_NM.equals("조식")){
                                    itemlist.add(data)
                                }
                                if(choose == 2 && data.MMEAL_SC_NM.equals("중식")){
                                    itemlist.add(data)
                                }
                                if(choose == 3 && data.MMEAL_SC_NM.equals("석식")){
                                    itemlist.add(data)
                                }
                                if(SimpleDateFormat("EE").format(date).equals("목") && choose == 3 && data.MMEAL_SC_NM.equals("석식")){
                                    itemlist.add(dietRow("집 가는 날", (data.MLSV_YMD.toInt() + 1).toString(), ""))
                                }
                            }
                        }
                    }
                }
                adapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<SchoolMealData>, t: Throwable) {
                println("여기 ${t}")
            }
        })
    }

    fun addList(i : Int){
        for(j in 1..i){
            itemlist.add(dietRow("", "", ""))
        }
        mode = 1
    }
}