package com.asmanmirza.schoolpen.presentation.main.host

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
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

            bottomNavBar.getTabAt(0)?.icon?.setColorFilter(Color.parseColor("#ffffff"), PorterDuff.Mode.SRC_IN)
            bottomNavBar.addOnTabSelectedListener(object:OnTabSelectedListener{
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    when(tab?.position){
                        0->{
                            //tab.icon = null;
                            //tab.text = "Home"
                            tab.icon?.setColorFilter(Color.parseColor("#ffffff"), PorterDuff.Mode.SRC_IN)
                            hideDp(true)
                            hideTopButtons(true)
                            ivMenu.visibility = View.VISIBLE
                            MainActivity.instance.updateStatusBarColor("#90F86005")
                            setNavGraph(R.navigation.host_graph)
                        }
                        1->{
                            //tab.icon = null;
                           // tab.text = "Learn"
                            tab.icon?.setColorFilter(Color.parseColor("#ffffff"), PorterDuff.Mode.SRC_IN)
                            hideDp(true)
                            hideTopButtons(true)
                            hideMenuIcon(true)
                            setNavBarColor("#00000000", "#259163D7")
                            setNavGraph(R.navigation.nav_learn)

                        }
                        2->{
                            //tab.icon = null;
                            //tab.text = "Classwork"
                            tab.icon?.setColorFilter(Color.parseColor("#ffffff"), PorterDuff.Mode.SRC_IN)
                            hideDp(true)
                            hideTopButtons(true)
                            hideMenuIcon(true)
                            setNavBarColor("#00000000", "#259163D7")
                            setNavGraph(R.navigation.nav_classwork)
                            //showContainer(home = false, learn = false, classWork = true, courses = false, performance = false)
                        }
                        3->{
                            //tab.icon = null;
                            //tab.text = "Courses"
                            tab.icon?.setColorFilter(Color.parseColor("#ffffff"), PorterDuff.Mode.SRC_IN)
                            hideDp(true)
                            hideTopButtons(true)
                            setNavBarColor("#00000000", "#259163D7")
                            setNavGraph(R.navigation.nav_courses)
                            //showContainer(home = false, learn = false, classWork = false, courses = true, performance = false)
                        }
                        4->{
                            tab.icon?.setColorFilter(Color.parseColor("#ffffff"), PorterDuff.Mode.SRC_IN)
                            //tab.icon = null;
                            //tab.text = "Performance"
                        }
                    }
                }

                @SuppressLint("UseCompatLoadingForDrawables")
                override fun onTabUnselected(tab: TabLayout.Tab?) {

                    when(tab?.position){
                        0->{
                           // tab.icon = resources.getDrawable(R.drawable.ic_round_home_24);
                            tab.icon?.setColorFilter(Color.parseColor("#d1d1d1"), PorterDuff.Mode.SRC_IN)
                            tab.text = ""
                        }
                        1->{
                            //tab.icon = resources.getDrawable(R.drawable.ic_learn);
                            tab.icon?.setColorFilter(Color.parseColor("#d1d1d1"), PorterDuff.Mode.SRC_IN)
                            tab.text = ""
                        }
                        2->{
                            //tab.icon = resources.getDrawable(R.drawable.ic_round_auto_stories_24);
                            tab.icon?.setColorFilter(Color.parseColor("#d1d1d1"), PorterDuff.Mode.SRC_IN)
                            tab.text = ""
                        }
                        3->{
                            //tab.icon = resources.getDrawable(R.drawable.ic_round_menu_book_24);
                            tab.icon?.setColorFilter(Color.parseColor("#d1d1d1"), PorterDuff.Mode.SRC_IN)
                            tab.text = ""
                        }
                        4->{
                            //tab.icon = resources.getDrawable(R.drawable.ic_graph);
                            tab.icon?.setColorFilter(Color.parseColor("#d1d1d1"), PorterDuff.Mode.SRC_IN)
                            tab.text = ""
                        }
                    }

                }

                override fun onTabReselected(tab: TabLayout.Tab?) {

                }


            })

        }

    }


    fun setNavGraph(id:Int){
        val myNavHostFragment: FragmentContainerView = binding.homeFragmentContainer
        val inflater = myNavHostFragment.findNavController().navInflater
        val graph = inflater.inflate(id)
        myNavHostFragment.findNavController().graph = graph
    }

    fun setNavBarColor(color:String, statusBarColor:String){
        MainActivity.instance.updateStatusBarColor(statusBarColor)
        binding.topNavBack.setBackgroundColor(Color.parseColor(color))
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