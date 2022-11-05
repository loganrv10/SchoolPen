package com.asmanmirza.schoolpen.presentation.main.Teacher.chat

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.asmanmirza.schoolpen.R
import com.asmanmirza.schoolpen.databinding.FragmentChatPToPBinding
import com.asmanmirza.schoolpen.databinding.FragmentTimeTableBinding
import com.asmanmirza.schoolpen.presentation.main.MainActivity
import com.asmanmirza.schoolpen.presentation.main.Teacher.host.TeacherHostFragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class ChatPToPFragment : Fragment() {

    private var _binding: FragmentChatPToPBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentChatPToPBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("UnsafeRepeatOnLifecycleDetector")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        MainActivity.instance.updateStatusBarColor("#ffffff")
        binding.apply {
            TeacherHostFragment.instance.hideNavButtons(true)
            Glide.with(requireContext()).load("https://api.lorem.space/image/face?w=150&h=150").thumbnail(0.5f).diskCacheStrategy(
                DiskCacheStrategy.ALL).into(dpImage)

            recChatPToP.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            recChatPToP.adapter = AdapterChatPToP(requireContext(), getDummyChats());

            btnBack.setOnClickListener {
                findNavController().popBackStack()
            }

        }
    }

    fun getDummyChats():ArrayList<ModelPToP>{
        return ArrayList<ModelPToP>().apply {
            add(ModelPToP("1", "Good afternoon sir, I am having some issue with the pythagoras theorem", "", "1 hours ago" ))
            add(ModelPToP("1", "I saw some examples but didn't even understand", "", "1 hours ago" ))
            add(ModelPToP("1", "Can you please send the solved example of this?", "", "1 hours ago" ))
            add(ModelPToP("0", "", "https://i.ytimg.com/vi/VJWzXLl8JX8/maxresdefault.jpg", "1 hours ago" ))
            add(ModelPToP("0", "Check this and try to solve the second program 🙂", "", "1 hours ago" ))
            add(ModelPToP("1", "Can you clear this in more detailed way?", "", "30 min ago" ))
            add(ModelPToP("0", "Ok let's see this", "", "15 min ago" ))
            add(ModelPToP("0", "Meet me during lunch.", "", "15 min ago" ))
        }

    }

}