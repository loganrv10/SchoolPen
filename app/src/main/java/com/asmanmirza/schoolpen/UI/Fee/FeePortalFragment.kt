package com.asmanmirza.schoolpen.UI.Fee

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.asmanmirza.schoolpen.R
import com.asmanmirza.schoolpen.UI.Fee.Adapter.Data
import com.asmanmirza.schoolpen.UI.Fee.Adapter.PaymentHistoryAdapter
import com.asmanmirza.schoolpen.databinding.FragmentClassworkBinding
import com.asmanmirza.schoolpen.databinding.FragmentFeeBinding

class FeePortalFragment : Fragment() {

    private var _binding: FragmentFeeBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: PaymentHistoryAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFeeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val data = mutableListOf(
            Data(1,"Title 1", "Description 1"),
            Data(2,"Title 2", "Description 2"),
            Data(3,"Title 3", "Description 3")

        )
        adapter = PaymentHistoryAdapter(requireContext()) {

        }

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                    // Change button shape back to rectangular
                    binding.floatingActionButton.backgroundTintList = ColorStateList.valueOf(resources.getColor(
                        R.color.blue))
//                    binding.floatingActionButton.shapeAppearanceModel =
//                        binding.floatingActionButton.shapeAppearanceModel.toBuilder().setAllCorners(CornerFamily.ROUNDED, resources.getDimension(R.dimen.default_corner_size)).build()
                } else {
                    // Change button shape to circular
                    binding.floatingActionButton.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.blue))
//                    binding.floatingActionButton.shapeAppearanceModel =
//                        binding.floatingActionButton.shapeAppearanceModel.toBuilder().setAllCorners(CornerFamily.CUT, resources.getDimension(R.dimen.default_corner_size)).build()
                }
            }
        })
    }

}