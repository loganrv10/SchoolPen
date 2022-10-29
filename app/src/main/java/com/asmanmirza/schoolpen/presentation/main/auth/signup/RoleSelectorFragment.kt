package com.asmanmirza.schoolpen.presentation.main.auth.signup

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.asmanmirza.schoolpen.R
import com.asmanmirza.schoolpen.databinding.FragmentLoginBinding
import com.asmanmirza.schoolpen.databinding.FragmentRoleSelectorBinding
import com.asmanmirza.schoolpen.di.ItemClickSupport
import com.asmanmirza.schoolpen.di.ItemClickSupport.OnItemClickListener
import com.google.android.flexbox.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RoleSelectorFragment : Fragment() {
    private var _binding: FragmentRoleSelectorBinding? = null
    private val binding get() = _binding!!
    lateinit var adapterRoleSelect: AdapterRoleSelect;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRoleSelectorBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {

            val layoutManager = FlexboxLayoutManager(requireContext())
            layoutManager.flexWrap = FlexWrap.WRAP
            layoutManager.flexDirection = FlexDirection.ROW
            layoutManager.justifyContent = JustifyContent.FLEX_START
            layoutManager.alignItems = AlignItems.FLEX_START
            recRoles.layoutManager = layoutManager

            adapterRoleSelect = AdapterRoleSelect(requireContext(), ArrayList<String>().apply {
                add("Teacher")
                add("Student")
                add("Parent")
            })

            recRoles.adapter = adapterRoleSelect;

            val anim:Animation = AnimationUtils.loadAnimation(requireContext(), R.anim.left_rise)
            title.startAnimation(anim)
            recRoles.startAnimation(anim)

            ItemClickSupport.addTo(recRoles).setOnItemClickListener(object:OnItemClickListener{
                override fun onItemClicked(recyclerView: RecyclerView?, position: Int, v: View?) {



                }
            })
            ItemClickSupport.addTo(recRoles).setOnItemClickListener { recyclerView, position, v ->
                adapterRoleSelect.selected = "$position"
                adapterRoleSelect.notifyDataSetChanged()
                btnNext.visibility = View.VISIBLE
            }

            val bundle = Bundle()

            btnNext.setOnClickListener {
                if(adapterRoleSelect.selected.isEmpty()){
                    Toast.makeText(requireContext(), "Please select a role", Toast.LENGTH_SHORT).show()
                }else if(adapterRoleSelect.selected.toInt() == 0){
                    bundle.putString("id", "1")
                    findNavController().navigate(R.id.action_roleSelectorFragment_to_signUpFragment, bundle)
                }else if(adapterRoleSelect.selected.toInt() == 1){
                    bundle.putString("id", "2")
                    findNavController().navigate(R.id.action_roleSelectorFragment_to_signUpFragment, bundle)
                }else if(adapterRoleSelect.selected.toInt() == 2){
                    bundle.putString("id", "3")
                    findNavController().navigate(R.id.action_roleSelectorFragment_to_signUpFragment, bundle)
                }
            }
        }
    }
}