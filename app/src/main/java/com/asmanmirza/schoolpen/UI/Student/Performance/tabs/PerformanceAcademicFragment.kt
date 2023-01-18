package com.asmanmirza.schoolpen.UI.Student.Performance.tabs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.asmanmirza.schoolpen.R
import com.asmanmirza.schoolpen.UI.Student.Performance.AdapterSubjectWiseMarks
import com.asmanmirza.schoolpen.UI.Student.Performance.ModelSubjectMarks
import com.asmanmirza.schoolpen.UI.Student.Performance.StudentPerformanceTabsAdapter
import com.asmanmirza.schoolpen.databinding.FragmentPerformanceAcademicBinding
import com.asmanmirza.schoolpen.databinding.FragmentStudentPerformanceBinding

class PerformanceAcademicFragment : Fragment() {


    private lateinit var viewBinding : FragmentPerformanceAcademicBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewBinding = FragmentPerformanceAcademicBinding.inflate(layoutInflater,container,false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.apply {

            recGradeCard.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            recGradeCard.adapter = AdapterSubjectWiseMarks(requireContext(), getMarks())

        }

    }

    fun getMarks():ArrayList<ModelSubjectMarks>{
        return ArrayList<ModelSubjectMarks>().apply {
            add(ModelSubjectMarks("", "Science", "8.2", "8.0", "7.2", "9.4"))
            add(ModelSubjectMarks("", "Math", "8.2", "8.0", "7.2", "9.4"))
            add(ModelSubjectMarks("", "English", "8.2", "8.0", "7.2", "9.4"))
            add(ModelSubjectMarks("", "Hindi", "8.2", "8.0", "7.2", "9.4"))
            add(ModelSubjectMarks("", "Social Science", "8.2", "8.0", "7.2", "9.4"))
        }
    }

}