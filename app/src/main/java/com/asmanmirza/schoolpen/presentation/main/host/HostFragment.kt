package com.asmanmirza.schoolpen.presentation.main.host

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.asmanmirza.schoolpen.R
import com.asmanmirza.schoolpen.databinding.FragmentHostBinding
import com.asmanmirza.schoolpen.databinding.FragmentLiveClassesBinding
import com.asmanmirza.schoolpen.presentation.main.MainActivity
import com.asmanmirza.schoolpen.presentation.main.courses.CoursesFragment
import com.asmanmirza.schoolpen.presentation.main.host.home.HomeFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener


class HostFragment : Fragment() {
    private val viewModel: HostViewModel by activityViewModels()

    private var _binding: FragmentHostBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        instance = this;
        _binding = FragmentHostBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            viewModel.user = it.getString("user").toString()
        }

        binding.apply {

            bottomNavBar.addOnTabSelectedListener(object:OnTabSelectedListener{
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    when(tab?.position){

                        0->{
                            tab.icon = null;
                            tab.text = "Home"
                            hideDp(true)
                            hideTopButtons(true)
                            ivMenu.visibility = View.VISIBLE
                            MainActivity.instance.updateStatusBarColor("#90F86005")
                            HomeFragment.instance.scrollToTop()
                            showContainer(home = true, learn = false, classWork = false, courses = false, performance = false)
                        }
                        1->{
                            tab.icon = null;
                            tab.text = "Learn"
                            hideDp(true)
                            hideTopButtons(true)
                            hideMenuIcon(true)
                            setNavBarColor("#00000000", "#259163D7")
                            showContainer(home = false, learn = true, classWork = false, courses = false, performance = false)
                        }
                        2->{
                            tab.icon = null;
                            tab.text = "Classwork"
                            hideDp(true)
                            hideTopButtons(true)
                            hideMenuIcon(true)
                            setNavBarColor("#00000000", "#259163D7")
                            showContainer(home = false, learn = false, classWork = true, courses = false, performance = false)
                        }
                        3->{
                            tab.icon = null;
                            tab.text = "Courses"
                            hideDp(true)
                            CoursesFragment.instance.scrollToTop()
                            hideTopButtons(true)
                            setNavBarColor("#00000000", "#259163D7")
                            showContainer(home = false, learn = false, classWork = false, courses = true, performance = false)
                        }
                        4->{
                            tab.icon = null;
                            tab.text = "Performance"
                        }
                    }
                }

                @SuppressLint("UseCompatLoadingForDrawables")
                override fun onTabUnselected(tab: TabLayout.Tab?) {

                    when(tab?.position){
                        0->{
                            tab.icon = resources.getDrawable(R.drawable.ic_round_home_24);
                            tab.text = ""

                        }
                        1->{
                            tab.icon = resources.getDrawable(R.drawable.ic_learn);
                            tab.text = ""
                        }
                        2->{
                            tab.icon = resources.getDrawable(R.drawable.ic_round_auto_stories_24);
                            tab.text = ""
                        }
                        3->{
                            tab.icon = resources.getDrawable(R.drawable.ic_round_menu_book_24);
                            tab.text = ""
                        }
                        4->{
                            tab.icon = resources.getDrawable(R.drawable.ic_graph);
                            tab.text = ""
                        }

                    }

                }

                override fun onTabReselected(tab: TabLayout.Tab?) {

                }


            })

        }

    }

    fun setNavBarColor(color:String, statusBarColor:String){
        MainActivity.instance.updateStatusBarColor(statusBarColor)
        binding.topNavBack.setBackgroundColor(Color.parseColor(color))
    }


    fun showContainer(home:Boolean, learn:Boolean, classWork:Boolean, courses:Boolean, performance:Boolean){

        if(home)
            binding.homeFragmentContainer.visibility = View.VISIBLE
        else
            binding.homeFragmentContainer.visibility = View.GONE

        if(classWork)
            binding.classworkFragmentContainer.visibility = View.VISIBLE
        else
            binding.classworkFragmentContainer.visibility = View.GONE

        if(courses)
            binding.coursesFragmentContainer.visibility = View.VISIBLE
        else
            binding.coursesFragmentContainer.visibility = View.GONE

        if(learn)
            binding.learnFragmentContainer.visibility = View.VISIBLE
        else
            binding.learnFragmentContainer.visibility = View.GONE
    }

    fun hideBottomNavBar(hide:Int){
        if(hide == 1){
            binding.bottomNavBar.visibility = View.GONE
            binding.topNavBack.visibility = View.GONE
        }else{
            binding.bottomNavBar.visibility = View.VISIBLE
            binding.topNavBack.visibility = View.VISIBLE
        }
    }

    fun hideTopButtons(hide:Boolean){
       if(hide){
           binding.layoutTopButtons.visibility = View.GONE
       }else{
           binding.layoutTopButtons.visibility = View.VISIBLE
       }
    }

    fun hideMenuIcon(boolean: Boolean){
        if(boolean){
            binding.ivMenu.visibility = View.GONE
        }else{
            binding.ivMenu.visibility = View.VISIBLE
        }

    }

    fun hideDp(hide:Boolean){
        if(hide){
            binding.topRightDp.visibility = View.GONE
        }else{
            binding.topRightDp.visibility = View.VISIBLE
        }
    }

    companion object {
        lateinit var instance: HostFragment
            private set
    }


}