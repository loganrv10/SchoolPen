package com.asmanmirza.schoolpen.presentation.main.teachersleave

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.asmanmirza.schoolpen.R
import com.asmanmirza.schoolpen.databinding.FragmentTeacherLeavesBinding

class TeacherLeavesFragment : Fragment() {

   private var _binding : FragmentTeacherLeavesBinding? = null
   private val binding get() = _binding!!


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.backButton.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        binding.sickLeaveMask.setOnClickListener {
            binding.vacationLeaveMask.setImageResource(0)
            binding.sickLeaveMask.setImageResource(R.drawable.img_fader_mask)
        }

        binding.vacationLeaveMask.setOnClickListener {
            binding.vacationLeaveMask.rotation=180.0F
        binding.vacationLeaveMask.setImageResource(R.drawable.img_fader_mask)
            binding.sickLeaveMask.setImageResource(0)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTeacherLeavesBinding.inflate(layoutInflater,container,false)
        return binding.root
    }


}