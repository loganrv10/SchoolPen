package com.asmanmirza.schoolpen.presentation.main.periods.studentperiods

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import com.asmanmirza.schoolpen.R
import com.asmanmirza.schoolpen.databinding.ActivityStudentPeriodsBinding

class StudentPeriodsActivity : AppCompatActivity() {
    private var _binding : ActivityStudentPeriodsBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_periods)
        _binding = ActivityStudentPeriodsBinding.inflate(layoutInflater)
        var fragmentManager = supportFragmentManager.beginTransaction()
        fragmentManager.replace(R.id.period_fragment_conatiner,StudentPeriodsFragment()).commit()
    }


}