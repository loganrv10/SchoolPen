package com.asmanmirza.schoolpen.presentation.main.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asmanmirza.schoolpen.common.NetworkResponse
import com.asmanmirza.schoolpen.data.api.repository.AuthServiceRepositoryImpl
import com.asmanmirza.schoolpen.domain.model.auth.AuthResponse
import com.google.gson.JsonObject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repo: AuthServiceRepositoryImpl
) : ViewModel() {
    private var _response = MutableStateFlow<NetworkResponse<AuthResponse>>(NetworkResponse.Idle())
    val response = _response.asStateFlow()

    fun login(json: JsonObject) {
        viewModelScope.launch {
            _response.value = NetworkResponse.Loading()
            repo.login(json).collectLatest {
                _response.value = it
            }
        }
    }

    fun register(json: JsonObject) {
        viewModelScope.launch {
            _response.value = NetworkResponse.Loading()
            repo.register(json).collectLatest {
                _response.value = it
            }
        }
    }

    fun generateOtp(json: JsonObject) {
        viewModelScope.launch {
            _response.value = NetworkResponse.Loading()
            repo.generateOtp(json).collectLatest {
                _response.value = it
            }
        }
    }

    fun verifyOtp(json: JsonObject) {
        viewModelScope.launch {
            _response.value = NetworkResponse.Loading()
            repo.verifyOtp(json).collectLatest {
                _response.value = it
            }
        }
    }
}