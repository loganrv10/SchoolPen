package com.asmanmirza.schoolpen.presentation.main.learn.create

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.asmanmirza.schoolpen.R
import com.asmanmirza.schoolpen.databinding.FragmentAddQuestionBinding
import com.asmanmirza.schoolpen.di.ItemClickSupport
import com.asmanmirza.schoolpen.presentation.main.MainActivity
import com.asmanmirza.schoolpen.presentation.main.host.HostFragment

class AddQuestionFragment : Fragment() {

    private var _viewBinding: FragmentAddQuestionBinding? = null
    private val binding get() = _viewBinding!!
    lateinit var students:ArrayList<ModelDp>;
    lateinit var selectedStudents:ArrayList<ModelDp>;
    lateinit var adapterOptions: AdapterOptions;
    lateinit var options:ArrayList<String>;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        instance = this
        _viewBinding = FragmentAddQuestionBinding.inflate(inflater,container,false)
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            HostFragment.instance.hideBottomNavBar(1)
            MainActivity.instance.updateStatusBarColor("#ffffff")
            getStudents()
            selectedStudents = ArrayList()
            recOptions.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            options = ArrayList()
            options.add("Option 1")
            options.add("Option 2")
            adapterOptions = AdapterOptions(requireContext(), options)
            recOptions.adapter = adapterOptions

            btnBack.setOnClickListener {
                findNavController().popBackStack()
            }

            btnAddOption.setOnClickListener {
                if(options.size + 1  <= 5){
                    options.add("Option ${options.size + 1}")
                    adapterOptions.notifyDataSetChanged()
                    if(options.size == 5){
                        btnAddOption.visibility = View.GONE
                    }
                }

            }
            recClassMem.layoutManager = GridLayoutManager(requireContext(), 5)
            recSelected.layoutManager = GridLayoutManager(requireContext(), 5)

            /*btnSelectAll.setOnClickListener {
                selectedStudents = students
                recSelected.adapter = AdapterSelectableDp(requireContext(), selectedStudents, true)
            }*/

            btnClearAll.setOnClickListener {
                selectedStudents.clear()
                recSelected.adapter = AdapterSelectableDp(requireContext(), selectedStudents, true)
            }

            ItemClickSupport.addTo(recClassMem).setOnItemClickListener { recyclerView, position, v ->
                val md = students[position]
                if(!selectedStudents.contains(md)) {
                    selectedStudents.add(md)
                    recSelected.adapter = AdapterSelectableDp(requireContext(), selectedStudents, true)
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    fun setSelectedSize(size:Int){
        //binding.btnSelectedPeoples.text = "$size people selected"
    }

    fun getStudents(){
        students = ArrayList();
        students.add(ModelDp("0", "https://api.lorem.space/image/face?w=150&h=150", false))
        students.add(ModelDp("1", "https://api.lorem.space/image/face?w=151&h=151", false))
        students.add(ModelDp("2", "https://api.lorem.space/image/face?w=152&h=152", false))
        students.add(ModelDp("3", "https://api.lorem.space/image/face?w=153&h=153", false))
        students.add(ModelDp("4", "https://api.lorem.space/image/face?w=154&h=154", false))
        students.add(ModelDp("5", "https://api.lorem.space/image/face?w=155&h=155", false))
        students.add(ModelDp("6", "https://api.lorem.space/image/face?w=156&h=156", false))
        students.add(ModelDp("7", "https://api.lorem.space/image/face?w=157&h=157", false))
        students.add(ModelDp("8", "https://api.lorem.space/image/face?w=158&h=158", false))
        students.add(ModelDp("9", "https://api.lorem.space/image/face?w=159&h=159", false))

        binding.recClassMem.adapter = AdapterSelectableDp(requireContext(), students, false)
    }

    companion object {
        lateinit var instance: AddQuestionFragment
            private set
    }


}