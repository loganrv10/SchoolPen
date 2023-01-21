package com.asmanmirza.schoolpen.UI.Fee.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.asmanmirza.schoolpen.databinding.ItemPaymentHistoryBinding

//ComparatorDiffUtil is a utility class that is used to compare two lists of items and calculate the differences between them.
// it is typically used in conjunction with the Android DiffUtil class to calculate the differences between two lists of items and display them in a RecyclerView.


class PaymentHistoryAdapter(val context: Context, private val onNoteClicked: (Data) -> Unit) :
    ListAdapter<Data, PaymentHistoryAdapter.NoteViewHolder>(ComparatorDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = ItemPaymentHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val image = getItem(position)
        image?.let {
            holder.bind(it)
        }
    }

    inner class NoteViewHolder(private val binding: ItemPaymentHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(imzData: Data) {

            binding.

        }

    }

    class ComparatorDiffUtil : DiffUtil.ItemCallback<Data>() {
        override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem == newItem
        }
    }
}

data class Data(val id: Int, val title: String, val description: String)
