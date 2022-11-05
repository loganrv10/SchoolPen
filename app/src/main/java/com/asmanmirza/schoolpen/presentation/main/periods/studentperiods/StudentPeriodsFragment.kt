package com.asmanmirza.schoolpen.presentation.main.periods.studentperiods

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.asmanmirza.schoolpen.R
import com.asmanmirza.schoolpen.databinding.FragmentStudentPeriodsBinding
import com.asmanmirza.schoolpen.presentation.main.periods.studentperiods.periodsadapter.AssignmentDataModel
import com.asmanmirza.schoolpen.presentation.main.periods.studentperiods.periodsadapter.StudentAssignmentAdapter
import java.util.*


class StudentPeriodsFragment : Fragment() {

    var _binding : FragmentStudentPeriodsBinding? = null
    val binding get() = _binding!!
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.readingContentPdf.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL, false)
        binding.readingContentPdf.adapter = StudentAssignmentAdapter(requireContext(),getAssignmentData())
        binding.exerciseContentPdf.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL, false)
        binding.exerciseContentPdf.adapter = StudentAssignmentAdapter(requireContext(),getExerciseData())
        binding.backButton.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentStudentPeriodsBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    private fun getAssignmentData():  ArrayList<AssignmentDataModel> {
        var list = ArrayList<AssignmentDataModel>()
        list.add(AssignmentDataModel(title="Shapes In Nature", subTitle = "5 min read",icon = R.drawable.ic_pdf_icon))
        list.add(AssignmentDataModel(title="Solids and voids: The shapes", subTitle = "10 min read",icon = R.drawable.ic_pdf_icon))
        list.add(AssignmentDataModel(title="Basic Geometric Shapes", subTitle = "2 min read",icon = R.drawable.ic_pdf_icon))
        return list
    }


    private fun getExerciseData():  ArrayList<AssignmentDataModel> {
        var list = ArrayList<AssignmentDataModel>()
        list.add(AssignmentDataModel(title="Questionnaire 1", subTitle = "5 Questions | 10 minutes",icon= R.drawable.ic_exercise_icon))
        list.add(AssignmentDataModel(title="Questionnaire 2", subTitle = "20 Questions | 30 minutes",icon= R.drawable.ic_exercise_icon))
        list.add(AssignmentDataModel(title="Questionnaire 3", subTitle = "3 Questions | 10 minutes",icon= R.drawable.ic_exercise_icon))
        return list
    }

}