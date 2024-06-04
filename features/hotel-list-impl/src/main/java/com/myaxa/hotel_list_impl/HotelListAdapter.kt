package com.myaxa.hotel_list_impl

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.myaxa.hotel_list_impl.databinding.ItemHotelBinding
import com.myaxa.hotel_list_impl.model.HotelUi
import javax.inject.Inject

class HotelListAdapter @Inject constructor(): ListAdapter<HotelUi, HotelItemViewHolder>(HotelDiffUtilCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotelItemViewHolder {

        val binding = ItemHotelBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return HotelItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HotelItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class HotelItemViewHolder(private val binding: ItemHotelBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: HotelUi) {

        val context = binding.root.context

        with(binding) {
            title.text = item.name
            address.text = item.address
            rating.text = item.stars.toString()
            distance.text = context.getString(R.string.distance, item.distance.toString())
            freeRoomsNumber.text = context.getString(R.string.free_rooms_number, item.freeRoomsNumber)
        }
    }
}

class HotelDiffUtilCallback(): DiffUtil.ItemCallback<HotelUi>() {
    override fun areItemsTheSame(oldItem: HotelUi, newItem: HotelUi): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: HotelUi, newItem: HotelUi): Boolean {
        return oldItem == newItem
    }
}