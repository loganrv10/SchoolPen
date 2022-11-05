package com.asmanmirza.schoolpen.presentation.main.live

import android.annotation.SuppressLint
import android.content.res.Resources
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
            recLiveClasses.adapter = AdapterLiveClasses(requireContext(), getLiveClasses())
            topBar.clipToOutline = true
            HostFragment.instance.hideBottomNavBar(1)
            HostFragment.instance.hideDp(true)
            HostFragment.instance.hideTopButtons(true)
            MainActivity.instance.updateStatusBarColor("#259163D7")

            btnBack.setOnClickListener {
                findNavController().popBackStack()
            }
            ItemClickSupport.addTo(recLiveClasses).setOnItemClickListener { recyclerView, position, v ->
                
                if(position == 0) {
                    findNavController().navigate(
                        R.id.action_liveClassFragment_to_liveClassDetail
                    )
                }else{
                    Toast.makeText(requireContext(), "You are not authorized to view all the live classes", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun getLiveClasses():ArrayList<ModelLiveClasses>{
        return ArrayList<ModelLiveClasses>().apply{
            add(ModelLiveClasses("", "History of India", "Sonu Sharma", "Social Science", "21", ""))
            add(ModelLiveClasses("", "Algebraic Expressions", "Nani Mathur", "Mathematics", "45", ""))
            add(ModelLiveClasses("", "Chemical Names", "D Jain", "Science", "32", ""))
            add(ModelLiveClasses("", "Q&A Session", "S Solanki", "English", "16", ""))
        }
    }

    

}