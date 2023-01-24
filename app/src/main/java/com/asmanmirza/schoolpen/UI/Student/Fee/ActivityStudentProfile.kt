package com.asmanmirza.schoolpen.UI.Student.Fee

import android.app.Dialog
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.asmanmirza.schoolpen.R
import com.asmanmirza.schoolpen.databinding.ActvityProfileStudentBinding

class ActivityStudentProfile : AppCompatActivity(), View.OnClickListener {
    private var _binding: ActvityProfileStudentBinding? = null
    private val binding get() = _binding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActvityProfileStudentBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        binding?.btnDesc?.setOnClickListener(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun openDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_student_invite)
        dialog.show()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnDesc -> {
                openDialog()
            }
        }
    }
}