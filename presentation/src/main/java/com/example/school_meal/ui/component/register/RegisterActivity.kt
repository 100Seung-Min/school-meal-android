package com.example.school_meal.ui.component.register

import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.example.school_meal.R
import com.example.school_meal.databinding.ActivityRegisterBinding
import com.example.school_meal.ui.component.base.BaseActivity
import com.example.school_meal.ui.component.login.LoginActivity
import com.example.school_meal.ui.extension.removeDot
import com.example.school_meal.viewmodel.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterActivity : BaseActivity<ActivityRegisterBinding>(R.layout.activity_register) {
    private val registerViewModel by viewModels<RegisterViewModel>()
    override fun init() {
        binding.register = this
    }

    override fun observe() {
        observeSchool()
        observeCurrentSchool()
    }

    private fun observeSchool() = registerViewModel.schoolInfo.observe(this) {
        SchoolListFragment().show(supportFragmentManager, "schoolList")
    }

    private fun observeCurrentSchool() = registerViewModel.currentSchool.observe(this) {
        binding.writeSchool.setText(it.schoolName.removeDot())
    }

    fun click(view: View) {
        when (view.id) {
            R.id.searchBtn -> {
                registerViewModel.schoolInfo(binding.writeSchool.text.toString())
            }
            R.id.certifyBtn -> {
                registerViewModel.sendMsg(binding.writePhone.text.toString())
                binding.writeCertify.visibility = View.VISIBLE
            }
            R.id.registerBtn -> {
                if (registerViewModel.currentSchool.value == null) {
                    Toast.makeText(this, "학교가 선택되지 않았습니다.", Toast.LENGTH_SHORT).show()
                } else if (binding.writeId.text.isNullOrBlank() || binding.writeClass.text.isNullOrBlank() || binding.writeClass.text.isNullOrBlank() || binding.writeName.text.isNullOrBlank() || binding.writePw.text.isNullOrBlank()) {
                    Toast.makeText(this, "필수 정보가 입력되지 않았습니다.", Toast.LENGTH_SHORT).show()
                } else if (binding.writePw.text.toString() != binding.writeRePw.text.toString()) {
                    Toast.makeText(this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
                }
//                else if (!binding.writeCertify.isVisible) {
//                    Toast.makeText(this, "핸드폰 인증을 진행해주세요..", Toast.LENGTH_SHORT).show()
//                }
//                else if (binding.writeCertify.text.toString() != registerViewModel.certifyNum.value) {
//                    Toast.makeText(this, "인증번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
//                }
                else {
                    registerViewModel.signUp(
                        binding.writeId.text.toString(),
                        binding.writePw.text.toString(),
                        binding.writePhone.text.toString(),
                        binding.writeClass.text.toString(),
                        binding.writeGrade.text.toString(),
                        binding.writeName.text.toString()
                    )
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                }
            }
        }
    }
}