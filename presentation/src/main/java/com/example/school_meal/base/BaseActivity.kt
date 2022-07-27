package com.example.school_meal.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import androidx.databinding.DataBindingUtil

abstract class BaseActivity<T: ViewDataBinding>(private val layoutId: Int): AppCompatActivity() {
    protected lateinit var binding: T
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutId)
        binding.lifecycleOwner = this
        viewSetting()
        observeEvent()
    }

    abstract fun viewSetting()

    abstract fun observeEvent()
}