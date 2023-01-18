package com.asmanmirza.schoolpen.Adapters

import android.widget.Switch
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.asmanmirza.schoolpen.UI.Teacher.Fragments.TeacherClassworkFragment
import com.asmanmirza.schoolpen.UI.Teacher.Fragments.TeacherExploreFragment
import com.asmanmirza.schoolpen.UI.Teacher.Fragments.TeachersHome

class PageAdapterTeachers(var fm:FragmentManager, var tabs:Int): FragmentPagerAdapter(fm, tabs) {

    override fun getCount(): Int {
        return tabs
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0->{
                TeachersHome()
            }
            1->{
                TeacherExploreFragment()
            }
            2->{
                TeacherClassworkFragment()
            }
            3->{
                TeachersHome()
            }
            else -> {
                TeachersHome()
            }
        }
    }
}