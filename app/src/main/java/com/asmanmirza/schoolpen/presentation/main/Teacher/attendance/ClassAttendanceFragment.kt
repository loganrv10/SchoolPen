package com.asmanmirza.schoolpen.presentation.main.Teacher.attendance

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.asmanmirza.schoolpen.R
import com.asmanmirza.schoolpen.databinding.FragmentClassAttendanceBinding
import com.asmanmirza.schoolpen.di.ItemClickSupport
import com.asmanmirza.schoolpen.di.ItemClickSupport.OnItemClickListener
import com.asmanmirza.schoolpen.presentation.main.Teacher.host.TeacherHostFragment
import com.asmanmirza.schoolpen.presentation.main.host.HostFragment

class ClassAttendanceFragment : Fragment() {
    private var _viewBinding: FragmentClassAttendanceBinding? = null
    private val viewBinding get() = _viewBinding!!
    lateinit var students:ArrayList<ModelAttendance>;
    lateinit var adapterAttendance: AdapterAttendance;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        instance = this
        // Inflate the layout for this fragment
        _viewBinding = FragmentClassAttendanceBinding.inflate(inflater,container,false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.apply {

            backButton.setOnClickListener {
                findNavController().popBackStack()
            }
            nextButton.setOnClickListener {
                Toast.makeText(requireContext(), "You cannot visit this section", Toast.LENGTH_SHORT).show()
            }
            TeacherHostFragment.instance.hideNavButtons(true)
            recAttendance.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            getStudents()
        }
    }

    fun checkPresent(data:ArrayList<ModelAttendance>){
        var present = 0
        for(i in 0 until data.size){
            val md = data[i]
            if(md.present){
                present += 1;
            }
        }
        viewBinding.textAttendanceStatsTotalPresent.text = if(present < 10) "0${present}" else present.toString()
        viewBinding.textAttendanceStatsTotalAbsent.text = if((data.size - present) < 10) "0${data.size - present}" else (data.size - present).toString()
    }

    fun getStudents(){
        students = ArrayList();
        students.add(ModelAttendance("", "Aman Patel", "", false))
        students.add(ModelAttendance("", "Amrit Tripathy", "", false))
        students.add(ModelAttendance("", "Aakash Lagwal", "", false))
        students.add(ModelAttendance("", "Aarti Nagpal", "", false))
        students.add(ModelAttendance("", "Bindiya Mithun", "", false))
        students.add(ModelAttendance("", "Brijesh Kumar", "", false))
        students.add(ModelAttendance("", "Chandan Nunia", "", false))
        students.add(ModelAttendance("", "Chetana Kumari", "", false))
        adapterAttendance = AdapterAttendance(requireContext(), students)
        viewBinding.textAttendanceStatsTotalAbsent.text = if(students.size < 10) "0${students.size}" else students.size.toString()
        viewBinding.recAttendance.adapter = adapterAttendance

    }

    companion object {
        lateinit var instance: ClassAttendanceFragment
            private set
    }
}