package com.asmanmirza.schoolpen.UI.Teacher.Home

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.asmanmirza.schoolpen.UI.Teacher.Fragments.JoinMeetingFragment
import com.asmanmirza.schoolpen.UI.Teacher.Fragments.TeacherSceduleClassFragment
import com.asmanmirza.schoolpen.UI.Teacher.Fragments.TeacherScheduleMeetingFragment
import com.asmanmirza.schoolpen.UI.Teacher.Fragments.TeacherStartLiveClassFragment
import com.asmanmirza.schoolpen.databinding.ActivityTeacherLiveClassBinding

class TeacherLiveClassActivity : AppCompatActivity() {

    lateinit var binding: ActivityTeacherLiveClassBinding

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        binding = ActivityTeacherLiveClassBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnStartLiveClass.setOnClickListener {
            var intent = Intent(this, TeacherStartLiveClassFragment::class.java)
            startActivity(intent)
        }

        binding.btnScheduleClass.setOnClickListener {
            var intent = Intent(this, TeacherSceduleClassFragment::class.java)
            startActivity(intent)
        }


        binding.btnScheduleMeeting.setOnClickListener {
            var intent = Intent(this, TeacherScheduleMeetingFragment::class.java)
            startActivity(intent)


        }

        binding.btnJoinMeeting.setOnClickListener {
            var intent = Intent(this, JoinMeetingFragment::class.java)
            startActivity(intent)
        }

    }
}





