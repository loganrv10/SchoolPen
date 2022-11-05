package com.asmanmirza.schoolpen.presentation.main.teachersleave

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import com.asmanmirza.schoolpen.R
import com.asmanmirza.schoolpen.databinding.ActivityTeachersLeaveBinding

class TeachersLeaveActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var fragmentManager = supportFragmentManager.beginTransaction()
        fragmentManager.replace(R.id.teachers_leaves_fragment_container,TeacherLeavesFragment()).commit()
        setContentView(R.layout.activity_teachers_leave)
    }

}