package com.asmanmirza.schoolpen.presentation.main.live

import android.annotation.SuppressLint
import android.content.res.Resources
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.asmanmirza.schoolpen.R
import com.asmanmirza.schoolpen.databinding.FragmentLiveClassesBinding
import com.asmanmirza.schoolpen.databinding.FragmentSignUpBinding
import com.asmanmirza.schoolpen.di.ItemClickSupport
import com.asmanmirza.schoolpen.di.ItemClickSupport.OnItemClickListener
import com.asmanmirza.schoolpen.presentation.main.MainActivity
import com.asmanmirza.schoolpen.presentation.main.host.HostFragment

class LiveClassesFragment : Fragment() {

    private var _binding: FragmentLiveClassesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLiveClassesBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("UnsafeRepeatOnLifecycleDetector")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            recLiveClasses.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
            recLiveClasses.adapter = AdapterLiveClasses(requireContext())
            topBar.clipToOutline = true
            HostFragment.instance.hideBottomNavBar(1)
            HostFragment.instance.hideDp(true)
            HostFragment.instance.hideTopButtons(true)
            MainActivity.instance.updateStatusBarColor("#259163D7")

            ItemClickSupport.addTo(recLiveClasses).setOnItemClickListener { recyclerView, position, v ->
                findNavController().navigate(
                    R.id.action_liveClassFragment_to_liveClassDetail
                )
            }
        }
    }

    

}