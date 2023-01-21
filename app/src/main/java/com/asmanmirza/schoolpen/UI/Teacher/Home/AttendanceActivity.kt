package com.asmanmirza.schoolpen.UI.Teacher.Home

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.asmanmirza.schoolpen.Adapters.AdapterAttendance
import com.asmanmirza.schoolpen.Helpers.ItemClickSupport
import com.asmanmirza.schoolpen.Helpers.ItemClickSupport.OnItemClickListener
import com.asmanmirza.schoolpen.Models.ModelAttendance
import com.asmanmirza.schoolpen.R
import com.asmanmirza.schoolpen.UI.Student.Classwork.details.HomeWorkDetailActivity
import com.asmanmirza.schoolpen.UI.Teacher.Fragments.TeacherStudentProfileFragment
import com.asmanmirza.schoolpen.databinding.ActivityAttendanceBinding
import java.util.Objects

class AttendanceActivity : AppCompatActivity() {

    lateinit var binding:ActivityAttendanceBinding
    lateinit var students:ArrayList<ModelAttendance>;
    lateinit var adapterAttendance: AdapterAttendance;

    companion object{
        lateinit var instance:AttendanceActivity
        private set
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAttendanceBinding.inflate(layoutInflater)
        setContentView(binding.root)
        instance = this
        updateData()
    }

    private fun updateData(){

        binding.apply {
            backButton.setOnClickListener {
                finish()
            }
            nextButton.setOnClickListener {
                Toast.makeText(this@AttendanceActivity, "You cannot visit this section", Toast.LENGTH_SHORT).show()
            }
            recAttendance.layoutManager = LinearLayoutManager(this@AttendanceActivity, LinearLayoutManager.VERTICAL, false)
            getStudents()

//            ItemClickSupport.addTo(recAttendance).setOnItemClickListener { recyclerView, position, v ->
//                startActivity(Intent(this@AttendanceActivity, TeacherStudentProfileFragment::class.java))
//            }
        }

    }

    fun checkPresent(data:ArrayList<ModelAttendance>){
        var present = 0
        for(i in 0 until data.size){
            val md = data[i]
            if(md.present){
                present += 1;
            }
        }
        binding.textAttendanceStatsTotalPresent.text = if(present < 10) "0${present}" else present.toString()
        binding.textAttendanceStatsTotalAbsent.text = if((data.size - present) < 10) "0${data.size - present}" else (data.size - present).toString()
    }

    fun getStudents(){
        students = ArrayList();
        students.add(ModelAttendance("", "Aman Patel", "", false))
        students.add(ModelAttendance("", "Amrit Tripathy", "", false))
        students.add(ModelAttendance("", "Aakash Lagwal", "", false))
        students.add(ModelAttendance("", "Aarti Nagpal", "", false))
        students.add(ModelAttendance("", "Bindiya Mithun", "", false))
        students.add(ModelAttendance("", "Brijesh Kumar", "", false))
        students.add(ModelAttendance("", "Chandan Nunia", "", false))
        students.add(ModelAttendance("", "Chetana Kumari", "", false))
        adapterAttendance = AdapterAttendance(this@AttendanceActivity, students)
        binding.textAttendanceStatsTotalAbsent.text = if(students.size < 10) "0${students.size}" else students.size.toString()
        binding.recAttendance.adapter = adapterAttendance
    }

}