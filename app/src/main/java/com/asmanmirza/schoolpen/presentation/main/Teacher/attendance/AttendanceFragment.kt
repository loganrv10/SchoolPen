package com.asmanmirza.schoolpen.presentation.main.Teacher.attendance

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.asmanmirza.schoolpen.R
import com.asmanmirza.schoolpen.databinding.FragmentAttendanceBinding

class AttendanceFragment : Fragment() {
    private var _viewBinding: FragmentAttendanceBinding? = null
    private val viewBinding get() = _viewBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _viewBinding = FragmentAttendanceBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.apply {
            btnAttendance.setOnClickListener {
                findNavController().navigate(R.id.action_attendanceFragment_to_classAttendanceFragment)
            }
        }
    }
}