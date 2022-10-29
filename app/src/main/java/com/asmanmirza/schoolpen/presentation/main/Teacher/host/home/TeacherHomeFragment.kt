package com.asmanmirza.schoolpen.presentation.main.Teacher.host.home

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
import com.asmanmirza.schoolpen.presentation.main.calander.AdapterTeacherEvents
import com.asmanmirza.schoolpen.presentation.main.calander.ModelDates
import com.asmanmirza.schoolpen.presentation.main.calander.ModelEvents
import com.asmanmirza.schoolpen.presentation.main.host.home.AdapterHomeDates

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
        viewBinding.startClass.btnStartClass.setOnClickListener {
            navigateToAttendance()
        }

        viewBinding.recDates.layoutManager = GridLayoutManager(requireContext(), 5)
        adapterHomeDates =  AdapterHomeDates(requireContext(), getDates(),"#C45162");
        viewBinding.recDates.adapter = adapterHomeDates

        viewBinding.recEvents.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        addEvents()

    }

    private fun navigateToAttendance(){
        findNavController().navigate(R.id.action_teacherHomeFragment_to_attendanceFragment)
    }

    fun getDates(): ArrayList<ModelDates> {

//        return ArrayList<ModelDates>().apply {
//            add(ModelDates(21, "21/10/2022", false))
//            add(ModelDates(22, "22/10/2022", false))
//            add(ModelDates(23, "23/10/2022", false))
//            add(ModelDates(24, "24/10/2022", false))
//            add(ModelDates(25, "25/10/2022", false))
//        }
        val arrayList = ArrayList<ModelDates>()
        arrayList.add(ModelDates(24, "24/10/2022", false))
        arrayList.add(ModelDates(25, "25/10/2022", false))
        arrayList.add(ModelDates(26, "26/10/2022", false))
        arrayList.add(ModelDates(27, "27/10/2022", false))
        arrayList.add(ModelDates(28, "28/10/2022", false))
        return arrayList
    }

    fun addEvents(){
        val events = ArrayList<ModelEvents>();
        events.add(ModelEvents(15, 10, "Annual sports meet", "All Day", "", "event"))
        events.add(ModelEvents(15, 10, "Award ceremony", "14:00-17:00", "", "event"))
        viewBinding.recEvents.adapter = AdapterTeacherEvents(requireContext(), events)
    }

}