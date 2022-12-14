package com.example.school_meal.ui.extension

import com.google.android.material.tabs.TabLayout

fun TabLayout.onTabSelected(action: (tab: TabLayout.Tab?) -> Unit) {
    addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
        override fun onTabSelected(tab: TabLayout.Tab?) {
            action(tab)
        }
        override fun onTabUnselected(tab: TabLayout.Tab?) = Unit
        override fun onTabReselected(tab: TabLayout.Tab?) = Unit
    })
}