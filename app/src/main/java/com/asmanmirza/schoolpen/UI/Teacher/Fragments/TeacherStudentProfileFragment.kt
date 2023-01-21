package com.asmanmirza.schoolpen.UI.Teacher.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.asmanmirza.schoolpen.R
import com.asmanmirza.schoolpen.databinding.FragmentTeacherStudentProfileBinding

class TeacherStudentProfileFragment : Fragment() {

    lateinit var binding: FragmentTeacherStudentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_teacher_student_profile, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fun getMonths(): ArrayList<String> {
            val months: ArrayList<String> = ArrayList();
            months.add("January")
            months.add("February")
            months.add("March")
            months.add("April")
            months.add("May")
            months.add("June")
            months.add("July")
            months.add("August")
            months.add("September")
            months.add("October")
            months.add("November")
            months.add("December")
            return months
        }
    }

}