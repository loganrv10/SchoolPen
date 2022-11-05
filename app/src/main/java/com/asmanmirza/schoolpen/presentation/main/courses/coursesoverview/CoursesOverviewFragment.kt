package com.asmanmirza.schoolpen.presentation.main.coursesoverview

import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.asmanmirza.schoolpen.R
import com.asmanmirza.schoolpen.databinding.FragmentCoursesOverviewBinding
import com.google.android.material.tabs.TabLayoutMediator


class CoursesOverviewFragment : Fragment() {

    var _binding : FragmentCoursesOverviewBinding? = null
    val binding  get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

       _binding = FragmentCoursesOverviewBinding.inflate(inflater,container,false)
        return binding.root
    }


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.backButton.setOnClickListener{
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        binding.backButton.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
        binding.enrollBtn.setTextColor(resources.getColor(R.color.white,resources.newTheme()))
        binding.viewPager.adapter = TabsAdapter(childFragmentManager,lifecycle)
        TabLayoutMediator(binding.tabLayout,binding.viewPager){tab,position->

            tab.text = when(position){
                0 -> "About Course"
                1 -> "Syllabus"
                2 -> "Instructor"
                3 -> "Reviews"
                4 -> "Certificate"
                else -> ""
            }
        }.attach()





    }

}