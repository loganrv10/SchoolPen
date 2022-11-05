package com.asmanmirza.schoolpen.presentation.main.auth.signup

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.asmanmirza.schoolpen.R
import com.asmanmirza.schoolpen.common.NetworkResponse
import com.asmanmirza.schoolpen.databinding.FragmentLoginBinding
import com.asmanmirza.schoolpen.databinding.FragmentSignUpBinding
import com.asmanmirza.schoolpen.di.TinyDB
import com.asmanmirza.schoolpen.presentation.main.auth.login.LoginViewModel
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONObject

@AndroidEntryPoint
class SignUpFragment : Fragment() {
    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!
    private var authorityID:String = "";
    private val viewModel: LoginViewModel by viewModels()
    lateinit var db: TinyDB;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("UnsafeRepeatOnLifecycleDetector")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //initializing local db
        db = TinyDB(context);

        binding.apply {
            //btnSignup

            btnBack.setOnClickListener {
                findNavController().popBackStack()
            }
            btnSignup.setOnClickListener {
                arguments?.let {
                    authorityID = it.getString("id").toString()
                }
                if(inFullName.text.toString().isEmpty()){
                    Toast.makeText(context, "Please enter your name", Toast.LENGTH_SHORT).show()
                }else if(inUsername.text.toString().isEmpty()){
                    Toast.makeText(context, "Please enter a username", Toast.LENGTH_SHORT).show()
                }else if(inEmail.text.toString().isEmpty()){
                    Toast.makeText(context, "Please enter your email", Toast.LENGTH_SHORT).show()
                }else if(inPassword.text.toString().isEmpty()){
                    Toast.makeText(context, "Please enter a password", Toast.LENGTH_SHORT).show()
                } else if(inPassword.text.toString().length < 6){
                    Toast.makeText(context, "Password must contain at least 6 characters", Toast.LENGTH_SHORT).show()
                }else{
                    val ids = JsonArray().apply {
                        add(JsonObject().apply {
                            addProperty("id", authorityID.toInt())
                        })
                    }
                    JsonObject().apply {
                        addProperty("userName", inUsername.text.toString().trim())
                        addProperty("password", inPassword.text.toString().trim())
                        addProperty("email", inEmail.text.toString().trim())
                        addProperty("name", inFullName.text.toString().trim())
                        add("roles", ids)
                    }.also {
                        viewModel.register(it)
                    }
                }
            }

        }
        fun ProgressBar.show(){
            binding.layoutLoading.visibility = View.VISIBLE
            binding.inUsername.isEnabled = false
            binding.inPassword.isEnabled = false
            binding.inFullName.isEnabled = false
            binding.inEmail.isEnabled = false
            binding.btnSignup.isEnabled = false
        }

        fun ProgressBar.dismiss(){
            binding.layoutLoading.visibility = View.GONE
            binding.inUsername.isEnabled = true
            binding.inPassword.isEnabled = true
            binding.btnSignup.isEnabled = true
            binding.inFullName.isEnabled = true
            binding.inEmail.isEnabled = true
        }
        try {
            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    viewModel.response.collectLatest { response ->
                        when (response) {
                            is NetworkResponse.Error -> {
                                println(response._message)
                                binding.layoutLoading.dismiss()
                            }
                            is NetworkResponse.Idle -> {}
                            is NetworkResponse.Loading -> {
                                binding.layoutLoading.show()
                            }
                            is NetworkResponse.Success -> {
                                try {
                                    binding.layoutLoading.dismiss()
                                    val obj = JSONObject(response._data.toString())
                                    val bundle = Bundle()
                                    bundle.putString("userId", obj.getString("userId"))
                                    db.putString("userId", obj.getString("userId"))
                                    db.putString("token", obj.getString("token"))
                                    findNavController()
                                        .navigate(
                                            R.id.action_signUpFragment_to_HostFragment,
                                            args = bundle
                                        )
                                    Toast.makeText(
                                        requireContext(),
                                        "Registered successfully",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }catch (e:Exception){
                                    Toast.makeText(requireContext(), "User already exists", Toast.LENGTH_SHORT).show()
                                }
                            }
                        }
                    }
                }
            }
        }catch (e:Exception){
            binding.layoutLoading.dismiss()
            e.printStackTrace()
        }
    }

}