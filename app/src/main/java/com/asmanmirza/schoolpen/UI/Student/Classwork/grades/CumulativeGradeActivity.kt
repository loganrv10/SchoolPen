package com.asmanmirza.schoolpen.UI.Student.Classwork.grades

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.asmanmirza.schoolpen.databinding.ActivityCumulativeGradeBinding

class CumulativeGradeActivity : AppCompatActivity() {

    lateinit var binding:ActivityCumulativeGradeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCumulativeGradeBinding.inflate(layoutInflater)
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