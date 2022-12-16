package com.example.school_meal.ui.component.register

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.entity.SchoolInfoEntity
import com.example.school_meal.databinding.FragmentSchoolListBinding
import com.example.school_meal.ui.adapter.SchoolAdapter
import com.example.school_meal.viewmodel.RegisterViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class SchoolListFragment(val schoolList: SchoolInfoEntity?): BottomSheetDialogFragment() {
    private val registerViewModel by activityViewModels<RegisterViewModel>()
    private lateinit var binding: FragmentSchoolListBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSchoolListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.schoolList.adapter = SchoolAdapter(schoolList?.row) {
            registerViewModel.setCurrentSchool(it)
            this@SchoolListFragment.dismiss()
        }
        binding.schoolList.layoutManager = LinearLayoutManager(context)
    }
}