package com.asmanmirza.schoolpen.presentation.main.courses

import android.graphics.Rect
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.widget.NestedScrollView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.asmanmirza.schoolpen.R
import com.asmanmirza.schoolpen.databinding.FragmentCoursesBinding
import com.asmanmirza.schoolpen.databinding.FragmentHomeBinding
import com.asmanmirza.schoolpen.presentation.main.host.HostFragment
import com.asmanmirza.schoolpen.presentation.main.host.home.HomeViewModel


class CoursesFragment : Fragment() {

    private var _binding: FragmentCoursesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        instance = this
        _binding = FragmentCoursesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            recResumeCourses.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            recTopCourses.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            recFriendCourses.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            recResumeCourses.adapter = AdapterCourses(requireContext())
            recTopCourses.adapter = AdapterCourses(requireContext())
            recFriendCourses.adapter = AdapterCourses(requireContext())
            scrollView.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
                if(isViewVisible(inSearch)){
                    floatingSearchButton.visibility = View.GONE
                }else{
                    floatingSearchButton.visibility = View.VISIBLE
                }
            })


            floatingSearchButton.setOnClickListener {
                scrollView.smoothScrollTo(0, 0)
            }
        }
    }

    private fun isViewVisible(view: View): Boolean {
        val scrollBounds = Rect()
        binding.scrollView.getDrawingRect(scrollBounds)
        val top = view.y
        val bottom = top + view.height
        return scrollBounds.top <= top && scrollBounds.bottom >= bottom
    }

    fun scrollToTop(){
        binding.scrollView.fullScroll(NestedScrollView.FOCUS_UP)
    }

    companion object {
        lateinit var instance: CoursesFragment
            private set
    }
}