package com.asmanmirza.schoolpen.presentation.main.classwork

import android.graphics.Rect
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.compose.animation.core.LinearEasing
import androidx.core.widget.NestedScrollView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.asmanmirza.schoolpen.R
import com.asmanmirza.schoolpen.databinding.FragmentClassworkBinding
import com.asmanmirza.schoolpen.databinding.FragmentHomeBinding
import com.asmanmirza.schoolpen.di.ItemClickSupport
import com.asmanmirza.schoolpen.di.ItemClickSupport.OnItemClickListener
import com.asmanmirza.schoolpen.presentation.main.MainActivity
import com.asmanmirza.schoolpen.presentation.main.host.HostFragment
import com.asmanmirza.schoolpen.presentation.main.host.home.HomeViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener

class ClassworkFragment : Fragment() {

    private var _binding: FragmentClassworkBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentClassworkBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            getHomeworkDetails()
            MainActivity.instance.updateStatusBarColor("#259163D7")
            HostFragment.instance.hideBottomNavBar(0)
            todayGradeCard.setOnClickListener {
                HostFragment.instance.hideBottomNavBar(1)
                findNavController().navigate(R.id.action_classFragment_to_c_gradingFragment)
            }

            cumulativeGradeBtn.setOnClickListener {
                HostFragment.instance.hideBottomNavBar(1)
                findNavController().navigate(R.id.action_classFragment_to_gradingFragment)
            }

            tabs.addOnTabSelectedListener(object:OnTabSelectedListener{
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    when(tab?.position){

                        0->{
                            getHomeworkDetails()
                            showView(layoutHomework, layoutTest, layoutScorecard)
                        }
                        1->{
                            getTestDetails()
                            showView(layoutTest, layoutHomework, layoutScorecard)
                        }
                        2->{
                            getScoreDetails()
                            showView(layoutScorecard, layoutHomework, layoutTest)
                        }

                    }
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {

                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                }
            })

            ItemClickSupport.addTo(recPendingWork).setOnItemClickListener { recyclerView, position, v ->
                findNavController().navigate(
                    R.id.action_classFragment_to_homeWorkDetail
                )
                HostFragment.instance.hideBottomNavBar(1)
            }
            ItemClickSupport.addTo(recPendingTest).setOnItemClickListener { recyclerView, position, v ->
                findNavController().navigate(
                    R.id.action_classFragment_to_testDetailFragment
                )
                HostFragment.instance.hideBottomNavBar(1)
            }


        }
    }

    fun showView(v1:View, v2:View, v3:View){
        v1.visibility = View.VISIBLE;
        v2.visibility = View.GONE
        v3.visibility = View.GONE
    }

    fun getHomeworkDetails(){
        binding.recCompletedWork.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.recPendingWork.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.recCompletedWork.adapter = AdapterCompleteWork(requireContext(), ArrayList());
        binding.recPendingWork.adapter = AdapterPendingHomework(requireContext(), ArrayList());
    }

    fun getTestDetails(){
        binding.recPendingTest.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.recUpcomingTest.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.recPendingTest.adapter = AdapterTest(requireContext(), getPendingTest());
        binding.recUpcomingTest.adapter = AdapterTest(requireContext(), getUpcomingTest());
    }

    fun getScoreDetails(){
        binding.recTeacherRemarks.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.recTeacherRemarks.adapter = AdapterTeacherRemarks(requireContext(), getRemarks())
    }

    private fun isViewVisible(view: View): Boolean {
        val scrollBounds = Rect()
        binding.scrollView.getDrawingRect(scrollBounds)
        val top = view.y
        val bottom = top + view.height
        return scrollBounds.top <= top && scrollBounds.bottom >= bottom
    }

    fun getPendingTest(): ArrayList<ModelTest>{
        return ArrayList<ModelTest>().apply{
            add(ModelTest("", "October Exam", "English", "29/10/2022", "100", "90 mins", R.drawable.abc, false))
        }
    }

    fun getUpcomingTest(): ArrayList<ModelTest>{
        return ArrayList<ModelTest>().apply {
            add(ModelTest("", "Unit test 1", "Science", "31/10/2022", "100", "90 mins", R.drawable.test_tube, true))
            add(ModelTest("", "MCQ Monthly", "English", "31/10/2022", "100", "90 mins", R.drawable.abc, true))
        }
    }

    fun getRemarks():ArrayList<ModelTeacherRemarks>{

        return ArrayList<ModelTeacherRemarks>().apply {
            add(ModelTeacherRemarks("", "Sonu Sharma", "You have to focus on your grammar, context is very good but lacks the way you write your answers.", "English", "1 hour ago", ""))
            add(ModelTeacherRemarks("", "Ujjwal Mittal", "Bring practical notebook to the class, we will conduct some experiments tomorrow.", "Physics", "4 hours ago", ""))
            add(ModelTeacherRemarks("", "D Kapoor", "Your progress is good, keep it up and do more hardwork", "Chemistry", "1 day ago", ""))

        }

    }

}