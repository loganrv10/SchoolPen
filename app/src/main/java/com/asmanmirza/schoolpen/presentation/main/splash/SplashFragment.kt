package com.asmanmirza.schoolpen.presentation.main.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.asmanmirza.schoolpen.R
import com.asmanmirza.schoolpen.databinding.FragmentSplashBinding
import kotlinx.coroutines.delay
/**
 * @Author: Asman Mirza
 * @Email: asman@otmalse.com
 * @Date: 28-09-2022
 * @Time: 20:57
 */
class SplashFragment : Fragment() {
    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launchWhenResumed {
            delay(3_000)
            findNavController().navigate(R.id.action_splashFragment_to_startFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}