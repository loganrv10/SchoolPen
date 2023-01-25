package com.asmanmirza.schoolpen.UI.Student.Fee

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.asmanmirza.schoolpen.R
import com.asmanmirza.schoolpen.databinding.ActvityProfileStudentBinding
import com.asmanmirza.schoolpen.databinding.DialogStudentInviteBinding

class ActivityStudentProfile : AppCompatActivity(), View.OnClickListener {
    private var _binding: ActvityProfileStudentBinding? = null
    private val binding get() = _binding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActvityProfileStudentBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        binding?.btnDesc?.setOnClickListener(this)
        binding?.ivEditProfileStu?.setOnClickListener(this)
        binding?.backButton?.setOnClickListener(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnDesc -> {
                val fragmentManager = supportFragmentManager
                val dialogFragment = DialogFragmentInvite()
                dialogFragment.show(fragmentManager, "dialog")
            }
            R.id.ivEditProfileStu -> {
                startActivity(
                    Intent(
                        this@ActivityStudentProfile,
                        ActivityEditProfileStudent::class.java
                    )
                )
            }
            R.id.back_button ->{
                finish()
            }
        }
    }
}