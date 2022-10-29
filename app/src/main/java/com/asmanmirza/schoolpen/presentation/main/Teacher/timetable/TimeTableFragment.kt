package com.asmanmirza.schoolpen.presentation.main.Teacher.timetable

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.asmanmirza.schoolpen.R
import com.asmanmirza.schoolpen.databinding.FragmentTimeTableBinding
import com.asmanmirza.schoolpen.di.ItemClickSupport
import com.asmanmirza.schoolpen.di.ItemClickSupport.OnItemClickListener
import com.asmanmirza.schoolpen.presentation.main.MainActivity
import java.text.SimpleDateFormat
import java.util.*

class TimeTableFragment : Fragment() {

    private var _binding: FragmentTimeTableBinding? = null
    private val binding get() = _binding!!
    lateinit var adapterWeekDays: AdapterWeekDays;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTimeTableBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("UnsafeRepeatOnLifecycleDetector")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        MainActivity.instance.updateStatusBarColor("#ffffff")
        binding.apply {

            recWeekDays.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapterWeekDays = AdapterWeekDays(requireContext(), getWeekDayNames())
            recWeekDays.adapter = adapterWeekDays

            recTimeTable.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            recTimeTable.adapter = AdapterTimeTable(requireContext(), getTimeTable())

            ItemClickSupport.addTo(recWeekDays).setOnItemClickListener(object:OnItemClickListener{
                @SuppressLint("NotifyDataSetChanged")
                override fun onItemClicked(recyclerView: RecyclerView?, position: Int, v: View?) {
                    adapterWeekDays.selected = position.toString()
                    adapterWeekDays.notifyDataSetChanged()
                }

            })

        }
    }

    fun getTimeTable():ArrayList<ModelTimeTable>{
        return ArrayList<ModelTimeTable>().apply {

            add(ModelTimeTable("", "Period 1", "Class V-C", "Science", "8:00", "8:40", false))
            add(ModelTimeTable("", "Period 2", "Class III", "Science", "8:40", "9:20", false))
            add(ModelTimeTable("", "Period 3", "Class VI-A", "Science", "9:20", "10:00", false))
            add(ModelTimeTable("", "Period 4", "Class VII-A", "Science", "10:00", "10:40", false))
            add(ModelTimeTable("", "Period 5", "Class V-B", "Science", "10:40", "11:20", false))
            add(ModelTimeTable("", "", "", "", "11:20", "12:00", true))
            add(ModelTimeTable("", "Period 6", "Class VI-A", "Science", "12:10", "12:40", false))
            add(ModelTimeTable("", "Period 7", "Class IV-C", "Science", "12:40", "1:20", false))
            add(ModelTimeTable("", "Period 7", "Class VII-B", "Science", "1:20", "2:00", false))

        }
    }

    fun getWeekDayNames():ArrayList<String>{

        return ArrayList<String>().apply {
            add("Mon")
            add("Tue")
            add("Wed")
            add("Thu")
            add("Fri")
            add("Sat")
        }

    }

}

class AdapterWeekDays(var context:Context, var data:ArrayList<String>):RecyclerView.Adapter<AdapterWeekDays.ViewHolder>(){

    var selected = "";

    class ViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView) {
        val name:TextView = itemView.findViewById(R.id.name);
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_timetable_weekday_name, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        if(selected.isEmpty()){
            if(data[position] == getCurrentWeekDay()){
                holder.name.background.setColorFilter(Color.parseColor("#ffffff"), PorterDuff.Mode.SRC_IN)
                holder.name.setTextColor(Color.parseColor("#C45162"))
            }else{
                holder.name.background.setColorFilter(Color.parseColor("#00000000"), PorterDuff.Mode.SRC_IN)
                holder.name.setTextColor(Color.parseColor("#ffffff"))
            }
        }else{
            if(selected.toInt() == position){
                holder.name.background.setColorFilter(Color.parseColor("#ffffff"), PorterDuff.Mode.SRC_IN)
                holder.name.setTextColor(Color.parseColor("#C45162"))
            }else{
                holder.name.background.setColorFilter(Color.parseColor("#00000000"), PorterDuff.Mode.SRC_IN)
                holder.name.setTextColor(Color.parseColor("#ffffff"))
            }
        }

        holder.name.text = data[position]
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun getCurrentWeekDay():String{
        val calendar: Calendar = Calendar.getInstance()
        val date: Date = calendar.time
        return SimpleDateFormat("EE", Locale.ENGLISH).format(date.time)
    }

}