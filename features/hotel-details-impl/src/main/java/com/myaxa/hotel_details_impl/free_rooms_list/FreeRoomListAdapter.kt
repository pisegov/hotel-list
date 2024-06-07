package com.myaxa.hotel_details_impl.free_rooms_list

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.myaxa.common.inflater
import com.myaxa.hotel_details_impl.databinding.ItemFreeRoomBinding
import com.myaxa.hotel_details_impl.di.HotelDetailsFragmentScope
import javax.inject.Inject

@HotelDetailsFragmentScope
internal class FreeRoomListAdapter @Inject constructor() : ListAdapter<String, FreeRoomViewHolder>(FreeRoomDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FreeRoomViewHolder {

        val binding = ItemFreeRoomBinding.inflate(parent.inflater, parent, false)
        return FreeRoomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FreeRoomViewHolder, position: Int) {
        holder.bind(currentList[position])
    }
}

internal class FreeRoomViewHolder(private val binding: ItemFreeRoomBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(number: String) {
        binding.number.text = number
    }
}

internal class FreeRoomDiffUtil : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean = oldItem == newItem
    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean = oldItem == newItem
}