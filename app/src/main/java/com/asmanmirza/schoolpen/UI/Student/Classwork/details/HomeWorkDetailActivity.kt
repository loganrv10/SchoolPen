package com.asmanmirza.schoolpen.UI.Student.Classwork.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.asmanmirza.schoolpen.databinding.ActivityHomeWorkDetailBinding

class HomeWorkDetailActivity : AppCompatActivity() {

    lateinit var binding:ActivityHomeWorkDetailBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeWorkDetailBinding.inflate(layoutInflater)
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