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
import com.asmanmirza.schoolpen.di.ItemClickSupport
import com.asmanmirza.schoolpen.di.ZoomOutPageTransformer
import com.asmanmirza.schoolpen.presentation.main.MainActivity
import com.asmanmirza.schoolpen.presentation.main.calander.AdapterEvents
import com.asmanmirza.schoolpen.presentation.main.calander.ModelDates
import com.asmanmirza.schoolpen.presentation.main.calander.ModelEvents
import com.asmanmirza.schoolpen.presentation.main.courses.CoursesFragment
import com.asmanmirza.schoolpen.presentation.main.host.HostFragment
import com.asmanmirza.schoolpen.presentation.main.host.HostViewModel
import com.asmanmirza.schoolpen.presentation.main.live.ModelLiveClasses
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
            HostFragment.instance.setNavBarColor("#00000000", "#99f86005")
            HostFragment.instance.hideDp(true)
            HostFragment.instance.hideTopButtons(true)


            with(viewPagerLiveClasses){
                adapter = AdapterHomeLiveClasses(requireContext(), getLiveClasses())
                setPageTransformer(true, ZoomOutPageTransformer())
                dotsIndicator.attachTo(this)
            }
            with(viewPagerTodaysClasses){
                adapter = AdapterHomeTodaysClasses(requireContext(), getTodayClasses())
                setPageTransformer(true, ZoomOutPageTransformer())
                dotsIndicator1.attachTo(this)
            }
            with(viewPagerTomorrowClasses){
                adapter = AdapterHomeTodaysClasses(requireContext(), getTomorrowClasses())
                setPageTransformer(true, ZoomOutPageTransformer())
                dotsIndicator2.attachTo(this)
            }

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

    fun showLiveClass(){
        findNavController().navigate(R.id.action_homeFragment_to_liveClassDetailFragment);
    }

    fun getLiveClasses():ArrayList<ModelLiveClasses>{
        return ArrayList<ModelLiveClasses>().apply{
            add(ModelLiveClasses("", "History of India", "Sonu Sharma", "Social Science", "21", ""))
            add(ModelLiveClasses("", "Algebraic Expressions", "Nani Mathur", "Mathematics", "45", ""))
            add(ModelLiveClasses("", "Chemical Names", "D Jain", "Science", "32", ""))
            add(ModelLiveClasses("", "Q&A Session", "S Solanki", "English", "16", ""))
        }
    }
    fun getTodayClasses():ArrayList<ModelClasses>{
        return ArrayList<ModelClasses>().apply{
            add(ModelClasses("", "1", "Social Science", "Sonu Sharma", "Chapter 2: History of India", ""))
            add(ModelClasses("", "2", "Mathematics", "Alex Edward", "Chapter 3: Combination & Permutation", ""))
            add(ModelClasses("", "3", "Science", "Ashley Jain", "Chapter 6: Chemical & Solutions", ""))
            add(ModelClasses("", "4", "English", "Danny Mathur", "Chapter 2: Active and Passive Voices", ""))
            add(ModelClasses("", "5", "Hindi", "S Gupta", "Chapter 4: Story of Buddha", ""))
            add(ModelClasses("", "6", "Computer", "Taylor", "Chapter 2: Learning basic of computer", ""))
        }
    }
    fun getTomorrowClasses():ArrayList<ModelClasses>{
        return ArrayList<ModelClasses>().apply{
            add(ModelClasses("", "1", "Social Science", "Sonu Sharma", "Chapter 2: History of India", ""))
            add(ModelClasses("", "2", "Mathematics", "Alex Edward", "Chapter 3: Combination & Permutation", ""))
            add(ModelClasses("", "3", "Science", "Ashley Jain", "Chapter 6: Chemical & Solutions", ""))
            add(ModelClasses("", "4", "English", "Danny Mathur", "Chapter 2: Active and Passive Voices", ""))
            add(ModelClasses("", "5", "Hindi", "S Gupta", "Chapter 4: Story of Buddha", ""))
            add(ModelClasses("", "6", "Computer", "Taylor", "Chapter 2: Learning basic of computer", ""))
        }
    }

    fun addEvents(){
        val events = ArrayList<ModelEvents>();
        events.add(ModelEvents(5, 11, "Annual sports meet", "All Day", "", "event"))
        events.add(ModelEvents(6, 11, "Award ceremony", "14:00-17:00", "", "event"))
        binding.recEvents.adapter = AdapterEvents(requireContext(), events)
    }

    fun addTasks(){
        val events = ArrayList<ModelEvents>();
        events.add(ModelEvents(8, 11, "Ankita Sharma assignment", "05:00 PM", "High", "task"))
        events.add(ModelEvents(10, 11, "Read Ch-2 B V Ramana", "05:00 PM", "Med", "task"))
        binding.recEvents.adapter = AdapterEvents(requireContext(), events)
    }

    fun addBirthdays(){
        val events = ArrayList<ModelEvents>();
        events.add(ModelEvents(23, 11, "Akhil's Birthday 🎂", "10:00 AM", "", "birthday"))
        events.add(ModelEvents(27, 11, "Principal Sir Birthday 🎂", "01:00 AM", "", "birthday"))
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
            add(ModelDates(4, "04/11/2022", false))
            add(ModelDates(5, "05/11/2022", false))
            add(ModelDates(6, "06/11/2022", false))
            add(ModelDates(7, "07/11/2022", false))
            add(ModelDates(8, "08/11/2022", false))
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