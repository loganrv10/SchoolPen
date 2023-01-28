package com.asmanmirza.schoolpen.UI.Teacher.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asmanmirza.schoolpen.UI.Student.di.ResultWrapper
import com.asmanmirza.schoolpen.UI.Student.models.DataUser
import com.asmanmirza.schoolpen.UI.Student.repository.UserDetailsRepo
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class ViewModelProfileTeacher @Inject constructor(private var repo: UserDetailsRepo) : ViewModel() {
    private val _userData: MutableLiveData<DataUser> = MutableLiveData()
    val userData: LiveData<DataUser> = _userData

    private val _errorString: MutableLiveData<String> = MutableLiveData()
    val errorString: LiveData<String> = _errorString

    fun getTeachersDetails(userId: Int, token: String) {
        viewModelScope.launch {
            repo.getUserDetails(userId, token).collectLatest {
                when (it) {
                    is ResultWrapper.GenericError -> {
                        val error = it.error?.message ?: "Error"
                        _errorString.postValue(error)
                    }
                    is ResultWrapper.Success -> {
                        val data = it.value.data
                        _userData.postValue(data)
                    }
                }
            }
        }
    }

}