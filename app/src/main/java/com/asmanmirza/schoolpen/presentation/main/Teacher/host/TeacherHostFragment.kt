package com.asmanmirza.schoolpen.presentation.main.Teacher.host

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.asmanmirza.schoolpen.R
import com.asmanmirza.schoolpen.databinding.FragmentTeacherHomeBinding
import com.asmanmirza.schoolpen.databinding.FragmentTeacherHostBinding
import com.asmanmirza.schoolpen.presentation.main.host.home.AdapterHomeDates

class TeacherHostFragment : Fragment() {

    private var _viewBinding: FragmentTeacherHostBinding? = null
    private val viewBinding get() = _viewBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _viewBinding = FragmentTeacherHostBinding.inflate(inflater,container,false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.apply {

        }

    }

}