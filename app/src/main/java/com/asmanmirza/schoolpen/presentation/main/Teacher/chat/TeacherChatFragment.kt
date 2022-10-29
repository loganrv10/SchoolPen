package com.asmanmirza.schoolpen.presentation.main.Teacher.chat

import android.annotation.SuppressLint
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
import com.asmanmirza.schoolpen.databinding.FragmentTeacherChatBinding
import com.asmanmirza.schoolpen.di.ItemClickSupport
import com.asmanmirza.schoolpen.di.ItemClickSupport.OnItemClickListener
import com.asmanmirza.schoolpen.presentation.main.MainActivity
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener


class TeacherFragment : Fragment() {

    private var _binding: FragmentTeacherChatBinding? = null
    private val binding get() = _binding!!
    lateinit var adapterTeachersChat: AdapterTeachersChat;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTeacherChatBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("UnsafeRepeatOnLifecycleDetector")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        MainActivity.instance.updateStatusBarColor("#ffffff")
        binding.apply {

            recTeachersChat.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapterTeachersChat = AdapterTeachersChat(requireContext(), getChats());
            recTeachersChat.adapter = adapterTeachersChat

            btnMore.setOnClickListener {
                //findNavController().navigate(R.id.action_teacherFragment_to_timeTableFragment)
            }

            tabs.addOnTabSelectedListener(object:OnTabSelectedListener{
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    when(tab?.position){

                        0->{
                            adapterTeachersChat = AdapterTeachersChat(requireContext(), getChats())
                            recTeachersChat.adapter = adapterTeachersChat
                        }
                        1->{

                            val data = getChats()
                            val temp = ArrayList<ModelTeacherChat>();
                            for(i in 0 until data.size){
                                val md = data[i]
                                if(!md.isTeacher){
                                    temp.add(md)
                                    adapterTeachersChat = AdapterTeachersChat(requireContext(), temp)
                                    recTeachersChat.adapter = adapterTeachersChat
                                }
                            }
                        }
                        2->{
                            val data = getChats()
                            val temp = ArrayList<ModelTeacherChat>();
                            for(i in 0 until data.size){
                                val md = data[i]
                                if(md.isTeacher){
                                    temp.add(md)
                                    adapterTeachersChat = AdapterTeachersChat(requireContext(), temp)
                                    recTeachersChat.adapter = adapterTeachersChat
                                }
                            }
                        }
                    }
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {

                }

                override fun onTabReselected(tab: TabLayout.Tab?) {

                }

            })

            ItemClickSupport.addTo(recTeachersChat).setOnItemClickListener(object:OnItemClickListener{
                override fun onItemClicked(recyclerView: RecyclerView?, position: Int, v: View?) {
                    findNavController().navigate(R.id.action_chatFragment_to_chatDetailFragment)
                }
            })

        }
    }

    fun getChats(): ArrayList<ModelTeacherChat> {

        return ArrayList<ModelTeacherChat>().apply {

            add(
                ModelTeacherChat(
                    "",
                    "Barun Iswarary",
                    "Class V",
                    "Meet me during lunch.",
                    0,
                    "23/10/2022",
                    "https://api.lorem.space/image/face?w=150&h=150",
                    isTeacher = false
                )
            )
            add(
                ModelTeacherChat(
                    "",
                    "Jain Edward",
                    "Class V",
                    "Ma'am, can you suggest best maths book from library?",
                    2,
                    "23/10/2022",
                    "https://api.lorem.space/image/face?w=151&h=151",
                    isTeacher = false
                )
            )
            add(
                ModelTeacherChat(
                    "",
                    "Rashmi Agarwal",
                    "Teacher",
                    "Are you up for today's meeting?",
                    1,
                    "23/10/2022",
                    "https://api.lorem.space/image/face?w=152&h=152",
                    isTeacher = true
                )
            )
            add(
                ModelTeacherChat(
                    "",
                    "Sonali Singh",
                    "Teacher",
                    "ok will be there.",
                    0,
                    "15/10/2022",
                    "https://api.lorem.space/image/face?w=153&h=153",
                    isTeacher = true
                )
            )
            add(
                ModelTeacherChat(
                    "",
                    "Nidhi Sharma",
                    "Teacher",
                    "Thank you",
                    0,
                    "12/10/2022",
                    "https://api.lorem.space/image/face?w=154&h=154",
                    isTeacher = true
                )
            )
        }
    }
}