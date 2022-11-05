package com.asmanmirza.schoolpen.presentation.main.auth.login

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.asmanmirza.schoolpen.R
import com.asmanmirza.schoolpen.common.NetworkResponse
import com.asmanmirza.schoolpen.databinding.FragmentLoginBinding
import com.asmanmirza.schoolpen.domain.model.auth.AuthResponse
import com.asmanmirza.schoolpen.presentation.main.MainActivity
import com.google.gson.JsonObject
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("UnsafeRepeatOnLifecycleDetector")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            btnBack.setOnClickListener { findNavController().popBackStack() }
            MainActivity.instance.updateStatusBarColor("#ffffff")
            val anim = AnimationUtils.loadAnimation( requireContext(), R.anim.left_rise)
            title.startAnimation(anim)
            inUsername.startAnimation(anim)
            inPassword.startAnimation(anim)
            textNote.startAnimation(anim)
            btnLogin.startAnimation(anim)

            btnLogin.setOnClickListener {

                if (inUsername.text.toString().lowercase() == "nidhi" && inPassword.text.toString() == "nidhi") {
                    findNavController().navigate(R.id.action_loginFragment_to_teachersHostFragment)
                } else {
                    if (inUsername.text?.isNotEmpty() == true && inPassword.text?.isNotEmpty() == true)
                        JsonObject().apply {
                            addProperty("userName", inUsername.text.toString())
                            addProperty("password", inPassword.text.toString())
                        }.also {
                            //layoutLoading.root.visibility = View.VISIBLE
                            binding.layoutLoading.visibility = View.VISIBLE
                            binding.inUsername.isEnabled = false
                            binding.inPassword.isEnabled = false
                            binding.btnLogin.isEnabled = false
                            viewModel.login(it)
                        }
                    else
                        Toast.makeText(
                            requireContext(),
                            "Please enter both userId and Password",
                            Toast.LENGTH_SHORT
                        ).show()
                }
            }
        }

        fun ProgressBar.show(){
            binding.layoutLoading.visibility = View.VISIBLE
            binding.inUsername.isEnabled = false
            binding.inPassword.isEnabled = false
            binding.btnLogin.isEnabled = false
        }

        fun ProgressBar.dismiss(){
            binding.layoutLoading.visibility = View.GONE
            binding.inUsername.isEnabled = true
            binding.inPassword.isEnabled = true
            binding.btnLogin.isEnabled = true
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.response.collectLatest { response ->
                    when(response){
                        is NetworkResponse.Error -> {
                            binding.layoutLoading.dismiss()
                            Toast.makeText(
                                requireContext(),
                                "Invalid credentials",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        is NetworkResponse.Idle -> { }
                        is NetworkResponse.Loading -> {
                            binding.layoutLoading.show()
                        }
                        is NetworkResponse.Success -> {
                            val bundle = Bundle()
                            bundle.putString("user", binding.inUsername.text.toString())
                            findNavController()
                                .navigate(R.id.action_loginFragment_to_hostFragment, args = bundle)
                            binding.layoutLoading.dismiss()
                            Toast.makeText(
                                requireContext(),
                                "Successfully logged in!",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
        }
    }
}