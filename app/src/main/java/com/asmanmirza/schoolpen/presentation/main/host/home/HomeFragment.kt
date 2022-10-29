package com.asmanmirza.schoolpen.presentation.main.host.home

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.asmanmirza.schoolpen.R
import com.asmanmirza.schoolpen.databinding.FragmentHomeBinding
import com.asmanmirza.schoolpen.presentation.main.MainActivity
import com.asmanmirza.schoolpen.presentation.main.calander.AdapterEvents
import com.asmanmirza.schoolpen.presentation.main.calander.ModelDates
import com.asmanmirza.schoolpen.presentation.main.calander.ModelEvents
import com.asmanmirza.schoolpen.presentation.main.courses.CoursesFragment
import com.asmanmirza.schoolpen.presentation.main.host.HostFragment
import com.asmanmirza.schoolpen.presentation.main.host.HostViewModel
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint


/**
 * @Author: Asman Mirza
 * @Email: asman@otmalse.com
 * @Date: 28-09-2022
 * @Time: 20:57
 */
@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var viewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val hViewModel: HostViewModel by activityViewModels()
    lateinit var adapterHomeDates: AdapterHomeDates;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        instance = this
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<TextView>(R.id.tv_user).text = hViewModel.user
        binding.apply {
            MainActivity.instance.updateStatusBarColor("#99F86005")
            HostFragment.instance.hideBottomNavBar(0)
            HostFragment.instance.hideDp(true)
            HostFragment.instance.hideTopButtons(true)
            btnViewAllNotices.setOnClickListener {
                MainActivity.instance.updateStatusBarColor("#ffffff")
                findNavController().navigate(R.id.action_homeFragment_to_noticeFragment)
            }

            btnViewAllLiveClasses.setOnClickListener {
                MainActivity.instance.updateStatusBarColor("#ffffff")
                findNavController().navigate(R.id.action_homeFragment_to_liveClassesFragment)
            }

            recDates.layoutManager = GridLayoutManager(requireContext(), 5)
            adapterHomeDates =  AdapterHomeDates(requireContext(), getDates(), "#9163d7");
            recDates.adapter = adapterHomeDates

            homeScroll.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
                HostFragment.instance.hideDp(isViewVisible(homeDp1))
                HostFragment.instance.hideTopButtons(isViewVisible(layoutTopButtons))

                if(isViewVisible(homeDp1)){
                    HostFragment.instance.setNavBarColor("#00000000", "#99F86005");
                }else{
                    HostFragment.instance.setNavBarColor("#E4874F", "#E4874F");
                }

            })

            btnOpenCalendar.setOnClickListener {
                MainActivity.instance.updateStatusBarColor("#ffffff")
                findNavController().navigate(R.id.action_homeFragment_to_calendarFragment)
            }

            recEvents.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            addEvents()
            tabs.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {
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
    }

    fun addEvents(){
        val events = ArrayList<ModelEvents>();
        events.add(ModelEvents(1, 11, "Annual sports meet", "All Day", "", "event"))
        events.add(ModelEvents(1, 11, "Award ceremony", "14:00-17:00", "", "event"))
        binding.recEvents.adapter = AdapterEvents(requireContext(), events)
    }

    fun addTasks(){
        val events = ArrayList<ModelEvents>();
        events.add(ModelEvents(31, 10, "Ankita Sharma assignment", "05:00 PM", "High", "task"))
        events.add(ModelEvents(31, 10, "Read Ch-2 B V Ramana", "05:00 PM", "Med", "task"))
        binding.recEvents.adapter = AdapterEvents(requireContext(), events)
    }

    fun addBirthdays(){
        val events = ArrayList<ModelEvents>();
        events.add(ModelEvents(30, 10, "Akhil's Birthday 🎂", "10:00 AM", "", "birthday"))
        events.add(ModelEvents(30, 10, "Principal Sir Birthday 🎂", "01:00 AM", "", "birthday"))
        binding.recEvents.adapter = AdapterEvents(requireContext(), events)
    }

    fun scrollToTop(){
        binding.homeScroll.fullScroll(NestedScrollView.FOCUS_UP)
    }

    companion object {
        lateinit var instance: HomeFragment
            private set
    }


    fun getDates():ArrayList<ModelDates>{

        return ArrayList<ModelDates>().apply {
            add(ModelDates(29, "29/10/2022", false))
            add(ModelDates(30, "30/10/2022", false))
            add(ModelDates(31, "31/10/2022", false))
            add(ModelDates(1, "1/11/2022", false))
            add(ModelDates(2, "2/11/2022", false))
        }

    }

    private fun isViewVisible(view: View): Boolean {
        val scrollBounds = Rect()
        binding.homeScroll.getDrawingRect(scrollBounds)
        val top = view.y
        val bottom = top + view.height
        return scrollBounds.top <= top && scrollBounds.bottom >= bottom
    }
}