package com.asmanmirza.schoolpen.UI.Student.Classwork.grades

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.asmanmirza.schoolpen.databinding.ActivityTodaysGradeBinding

class TodaysGradeActivity : AppCompatActivity() {

    lateinit var binding:ActivityTodaysGradeBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTodaysGradeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        updateData()
    }

    private fun updateData(){

        binding.apply {

            btnBack.setOnClickListener {
                finish()
            }

        }

    }

}