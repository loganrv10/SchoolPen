package com.asmanmirza.schoolpen.UI.Teacher.Profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.asmanmirza.schoolpen.Helpers.TinyDB
import com.asmanmirza.schoolpen.R
import com.asmanmirza.schoolpen.SchoolApp
import com.asmanmirza.schoolpen.UI.Student.Fee.viewModels.ViewModelProfile
import com.asmanmirza.schoolpen.UI.Teacher.viewModel.MainViewModelTeacherFactory
import com.asmanmirza.schoolpen.UI.Teacher.viewModel.ViewModelProfileTeacher
import com.asmanmirza.schoolpen.databinding.ActivityProfileTeacherBinding
import javax.inject.Inject

class ProfileTeacherActivity : AppCompatActivity() , View.OnClickListener{

    lateinit var binding:ActivityProfileTeacherBinding
    lateinit var userDetailViewModel: ViewModelProfileTeacher

    @Inject
    lateinit var mainViewModelFactory: MainViewModelTeacherFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileTeacherBinding.inflate(layoutInflater)
        setContentView(binding.root)
        updateData()
        val db = TinyDB(this);
        (application as SchoolApp).applicationComponent.inject(this)

        userDetailViewModel =
            ViewModelProvider(this, mainViewModelFactory)[ViewModelProfileTeacher::class.java]
        binding.editPersonalInfo.setOnClickListener(this)
        binding.backButton.setOnClickListener(this)
        val token = db.getString("token")
        userDetailViewModel.getTeachersDetails(
            5, "Bearer $token"
        )

        userDetailViewModel.userData.observe(this) {
            binding.profileName.text = it.userName
            binding.contentEmail.text = it.email
        }

    }

    private fun updateData(){

        binding.apply {

            backButton.setOnClickListener {
                finish()
            }

        }

    }

    override fun onClick(p: View?) {
        when (p?.id) {
            R.id.edit_personal_info -> {}
        }
    }
}