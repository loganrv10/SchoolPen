package com.asmanmirza.schoolpen.presentation.main.auth.signup

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.asmanmirza.schoolpen.R
import com.asmanmirza.schoolpen.common.NetworkResponse
import com.asmanmirza.schoolpen.databinding.FragmentCreateAccountBinding
import com.asmanmirza.schoolpen.presentation.main.auth.login.LoginViewModel
import com.google.gson.JsonObject
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.json.JSONObject

@AndroidEntryPoint
class CreateAccountFragment : Fragment() {
    private var _binding: FragmentCreateAccountBinding? = null
    private val binding get() = _binding!!
    private val viewModel: LoginViewModel by viewModels()
    private var API_STATE = 0;
    private var sessionID = "";
    var previousMobileNumber = "";

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCreateAccountBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("UnsafeRepeatOnLifecycleDetector", "SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            btnBack.setOnClickListener { findNavController().popBackStack() }

            inPhone.addTextChangedListener(object:TextWatcher{
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                }

                @SuppressLint("SetTextI18n")
                override fun afterTextChanged(s: Editable?) {
                    if(inPhone.text.toString().length == 10){

                        btnVerify.text = "Send otp"
                        btnVerify.visibility = View.VISIBLE
                    }else{
                        API_STATE = 0
                    }
                }
            })

            btnVerify.setOnClickListener {
                if(sessionID.isEmpty()){
                    if(inPhone.text.toString().length != 10){
                        Toast.makeText(context, "Please enter 10 digit mobile number", Toast.LENGTH_SHORT).show()
                    }else{
                        sendOTP(inPhone.text.toString())
                    }
                }else{
                    if(inOTP.text.toString().length != 4){
                        Toast.makeText(context, "Please enter a valid otp", Toast.LENGTH_SHORT).show()
                    }else{
                        val userID = arguments?.getString("userId")
                        val otp = inOTP.text.toString()
                        JsonObject().apply {
                            addProperty("id", userID)
                            addProperty("sessionId", sessionID)
                            addProperty("otp", otp)
                        }.also {
                            println(it)
                            viewModel.verifyOtp(it)
                        }
                    }
                }
            }
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.response.collectLatest { response ->
                    when(response){
                        is NetworkResponse.Error -> {}
                        is NetworkResponse.Idle -> {}
                        is NetworkResponse.Loading -> { }
                        is NetworkResponse.Success -> {
                            if(API_STATE == 0 && sessionID.isEmpty()){
                                API_STATE = 1;
                                val parentObj = JSONObject(response._data.toString())
                                sessionID = parentObj.getJSONObject("data").getJSONObject("result").getString("Details")
                                binding.btnVerify.text = "Verify and continue"
                                Toast.makeText(context, "OTP Sent successfully", Toast.LENGTH_SHORT).show()
                            }else{
                                val parentObj = JSONObject(response._data.toString())
                                if(parentObj.getBoolean("authenticated")){
                                    Toast.makeText(context, "Registered successfully", Toast.LENGTH_SHORT).show()
                                    findNavController().navigate(R.id.action_createAccountFragment_to_hostFragment)
                                }else{
                                    Toast.makeText(context, "Invalid OTP", Toast.LENGTH_SHORT).show()
                                }
                            }
                        }
                    }
                }
            }
        }

    }

    fun sendOTP(number:String){
        val userId = arguments?.let {
            it.getString("userId")
        }
        binding.inPhone.clearFocus()
        JsonObject().apply {
            addProperty("id", userId)
            addProperty("mobileNumber", number)
        }.also {
            println(it)
            viewModel.generateOtp(it)
        }
    }


}