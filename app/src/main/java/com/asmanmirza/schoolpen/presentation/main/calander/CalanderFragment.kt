package com.asmanmirza.schoolpen.presentation.main.calander

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.asmanmirza.schoolpen.R
import com.asmanmirza.schoolpen.databinding.FragmentCalanderBinding
import com.asmanmirza.schoolpen.di.ItemClickSupport
import com.asmanmirza.schoolpen.di.ItemClickSupport.OnItemClickListener
import com.asmanmirza.schoolpen.presentation.main.MainActivity
import com.asmanmirza.schoolpen.presentation.main.host.HostFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class CalanderFragment : Fragment() {

    private var _binding: FragmentCalanderBinding? = null
    private val binding get() = _binding!!
    lateinit var adapterMonths: AdapterMonths;
    lateinit var adapterDates: AdapterDates;
    lateinit var dates :ArrayList<ModelDates>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCalanderBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("UnsafeRepeatOnLifecycleDetector")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            HostFragment.instance.hideDp(true)
            HostFragment.instance.hideTopButtons(true)
            HostFragment.instance.hideBottomNavBar(1)
            MainActivity.instance.updateStatusBarColor("#ffffff")

            btnCreateTask.setOnClickListener {
                findNavController().navigate(R.id.action_calendarFragment_to_createTaskFragment)
            }

            btnBack.setOnClickListener {
                findNavController().popBackStack()
            }

            val layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            recMonths.layoutManager = layoutManager;
            val cal: Calendar = Calendar.getInstance()
            cal.time = Date()
            val month: Int = cal.get(Calendar.MONTH)
            getDates(month + 1)
            adapterMonths = AdapterMonths(requireContext(), getMonths())
            recMonths.adapter = adapterMonths
            recMonths.scrollToPosition(month)
            recDates.layoutManager = GridLayoutManager(requireContext(), 7);

            ItemClickSupport.addTo(recMonths).setOnItemClickListener(object : OnItemClickListener {
                @SuppressLint("NotifyDataSetChanged")
                override fun onItemClicked(recyclerView: RecyclerView?, position: Int, v: View?) {
                    adapterMonths.selected = position
                    adapterMonths.notifyDataSetChanged()
                    getDates(adapterMonths.selected + 1)
                }
            })
            recEvents.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            addEvents()
            tabs.addOnTabSelectedListener(object:OnTabSelectedListener{
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



    @SuppressLint("SimpleDateFormat")
    fun getDates(month: Int) {
        dates = ArrayList<ModelDates>();
        val selectedMonthDays =
            getTotalNumberOfDaysInMonth(binding.selectedCalander.text.toString().toInt(), month - 1)
        var nextMonthDays = 1
        /*if (month + 1 > 12) {
            nextMonthDays =
                getTotalNumberOfDaysInMonth(binding.selectedCalander.text.toString().toInt() + 1, 1)
        } else {
            nextMonthDays = getTotalNumberOfDaysInMonth(
                binding.selectedCalander.text.toString().toInt(),
                month + 1
            )
        }*/

        val previousMonthDays = if (month - 1 < 1) {
            getTotalNumberOfDaysInMonth(
                binding.selectedCalander.text.toString().toInt() - 1,
                11
            )
        } else {
            getTotalNumberOfDaysInMonth(
                binding.selectedCalander.text.toString().toInt(),
                month - 2
            )
        }

        val c = Calendar.getInstance()
        val inputDate = "01/" + "${if(month < 10) "0$month" else "$month"}/" + "${binding.selectedCalander.text}"
        val format1 = SimpleDateFormat("dd/MM/yyyy")
        val dt1: Date = format1.parse(inputDate) as Date
        c.time = dt1
        val firstDayPosition = if (c[Calendar.DAY_OF_WEEK] - 1 == 0) {
            7;
        } else {
            c[Calendar.DAY_OF_WEEK] - 1
        }

        println("$firstDayPosition _ $inputDate _ $selectedMonthDays _ $previousMonthDays")

        for (i in 1 until 36) {
            if (firstDayPosition != 1) {
                if (i < firstDayPosition) {
                    dates.add(
                        ModelDates(
                            previousMonthDays - (firstDayPosition - i - 1),
                            getDate("${previousMonthDays - (firstDayPosition - i - 1)}", month - 1),
                            true
                        )
                    )
                }else {
                    if (i - (firstDayPosition - 1) <= selectedMonthDays) {
                        dates.add(
                            ModelDates(
                                i - (firstDayPosition - 1),
                                getDate("${i - (firstDayPosition - 1)}", month),
                                false
                            )
                        )

                    }else{
                        dates.add(
                            ModelDates(nextMonthDays,
                                getDate("$nextMonthDays", month + 1),
                                true
                            )
                        )
                        nextMonthDays++;
                    }
                }

            }else{
                if (i <= selectedMonthDays) {
                    dates.add(
                        ModelDates(
                            i,
                            getDate("$i", month),
                            false
                        )
                    )

                }else{
                    dates.add(
                        ModelDates(nextMonthDays,
                            getDate("$nextMonthDays", month + 1),
                            true
                        )
                    )
                    nextMonthDays++;
                }
            }

        }

        adapterDates = AdapterDates(requireContext(), dates);
        binding.recDates.adapter  = adapterDates;

    }

    @SuppressLint("SimpleDateFormat")
    fun getDate(date:String, month:Int): String{
        return if(month - 1 == 0){
            "${if(date.toInt() < 10) "0$date" else date}/12/${binding.selectedCalander.text.toString().toInt() - 1}"
        }else if(month > 12){
            "${if(date.toInt() < 10) "0$date" else date}/01/${binding.selectedCalander.text.toString().toInt() + 1}"
        }else{
            "${if(date.toInt() < 10) "0$date" else date}/${if(month < 10) "0$month" else "$month"}/${binding.selectedCalander.text.toString()}"
        }
    }


    fun getTotalNumberOfDaysInMonth(year: Int, month: Int): Int {
        val currentMonth = GregorianCalendar(year, month, 1)
        return currentMonth.getActualMaximum(Calendar.DAY_OF_MONTH)
    }

    private fun getMonths(): ArrayList<String> {
        val months: ArrayList<String> = ArrayList();
        months.add("January")
        months.add("February")
        months.add("March")
        months.add("April")
        months.add("May")
        months.add("June")
        months.add("July")
        months.add("August")
        months.add("September")
        months.add("October")
        months.add("November")
        months.add("December")
        return months
    }

}