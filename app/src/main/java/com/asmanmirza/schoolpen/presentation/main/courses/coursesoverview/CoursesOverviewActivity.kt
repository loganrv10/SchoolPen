package com.asmanmirza.schoolpen.presentation.main.coursesoverview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import com.asmanmirza.schoolpen.R
import com.asmanmirza.schoolpen.databinding.ActivityCoursesOverviewBinding

class CoursesOverviewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCoursesOverviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCoursesOverviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var fragmentManager = supportFragmentManager.beginTransaction()
        fragmentManager.replace(R.id.fragment_container,CoursesOverviewFragment()).commit()

    }

}