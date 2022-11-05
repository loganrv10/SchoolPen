package com.asmanmirza.schoolpen.presentation.main.Teacher.host.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.asmanmirza.schoolpen.R
import com.asmanmirza.schoolpen.databinding.FragmentTeacherHomeBinding
import com.asmanmirza.schoolpen.presentation.main.Teacher.host.TeacherHostFragment
import com.asmanmirza.schoolpen.presentation.main.calander.AdapterEvents
import com.asmanmirza.schoolpen.presentation.main.calander.AdapterTeacherEvents
import com.asmanmirza.schoolpen.presentation.main.calander.ModelDates
import com.asmanmirza.schoolpen.presentation.main.calander.ModelEvents
import com.asmanmirza.schoolpen.presentation.main.host.home.AdapterHomeDates
import com.asmanmirza.schoolpen.presentation.main.teachersleave.TeachersLeaveActivity
import com.google.android.material.tabs.TabLayout

class TeacherHomeFragment : Fragment() {
    private var _viewBinding: FragmentTeacherHomeBinding? = null
    private val viewBinding get() = _viewBinding!!
    lateinit var adapterHomeDates: AdapterHomeDates;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _viewBinding = FragmentTeacherHomeBinding.inflate(inflater,container,false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        TeacherHostFragment.instance.hideNavButtons(false)
        viewBinding.startClass.btnStartClass.setOnClickListener {
            navigateToAttendance()
        }

        viewBinding.btnChat.setOnClickListener{
            findNavController().navigate(R.id.action_teacherHomeFragment_to_teacherChatFragment)
        }
        viewBinding.btnTimeTableToday.setOnClickListener {
            findNavController().navigate(R.id.action_teacherHomeFragment_to_timeTableFragment)
        }
        viewBinding.teachersLeavesTab.setOnClickListener {
            startActivity(Intent(requireContext(), TeachersLeaveActivity::class.java))
        }
        viewBinding.recDates.layoutManager = GridLayoutManager(requireContext(), 5)
        adapterHomeDates =  AdapterHomeDates(requireContext(), getDates(),"#C45162");
        viewBinding.recDates.adapter = adapterHomeDates

        viewBinding.recEvents.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        viewBinding.tabs.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {

                when (tab?.position) {
                    0 -> {
                        addEvents()
                    }
                    1 -> {
                        addTasks()
                    }
                    2 -> {
                        addBirthdays()
                    }
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })

    }
    private fun navigateToAttendance(){
        findNavController().navigate(R.id.action_teacherHomeFragment_to_attendanceFragment)
    }

    fun getDates():ArrayList<ModelDates>{

        return ArrayList<ModelDates>().apply {
            add(ModelDates(4, "04/11/2022", false))
            add(ModelDates(5, "05/11/2022", false))
            add(ModelDates(6, "06/11/2022", false))
            add(ModelDates(7, "07/11/2022", false))
            add(ModelDates(8, "08/11/2022", false))
        }

    }
    fun addEvents(){
        val events = ArrayList<ModelEvents>();
        events.add(ModelEvents(5, 11, "Annual sports meet", "All Day", "", "event"))
        events.add(ModelEvents(6, 11, "Award ceremony", "14:00-17:00", "", "event"))
        viewBinding.recEvents.adapter = AdapterEvents(requireContext(), events)
    }

    fun addTasks(){
        val events = ArrayList<ModelEvents>();
        events.add(ModelEvents(8, 11, "Ankita Sharma assignment", "05:00 PM", "High", "task"))
        events.add(ModelEvents(10, 11, "Read Ch-2 B V Ramana", "05:00 PM", "Med", "task"))
        viewBinding.recEvents.adapter = AdapterEvents(requireContext(), events)
    }

    fun addBirthdays(){
        val events = ArrayList<ModelEvents>();
        events.add(ModelEvents(23, 11, "Akhil's Birthday 🎂", "10:00 AM", "", "birthday"))
        events.add(ModelEvents(27, 11, "Principal Sir Birthday 🎂", "01:00 AM", "", "birthday"))
        viewBinding.recEvents.adapter = AdapterEvents(requireContext(), events)
    }
}