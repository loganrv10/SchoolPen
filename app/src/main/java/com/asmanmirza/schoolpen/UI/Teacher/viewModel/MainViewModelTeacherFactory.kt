package com.asmanmirza.schoolpen.UI.Teacher.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.asmanmirza.schoolpen.UI.Student.Fee.viewModels.ViewModelProfile
import com.asmanmirza.schoolpen.UI.Student.repository.UserDetailsRepo
import javax.inject.Inject

class MainViewModelTeacherFactory @Inject constructor(private val repo: UserDetailsRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ViewModelProfileTeacher(repo) as T

    }
}