package com.myaxa.hotel_list_impl

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.myaxa.common.inflater
import com.myaxa.common.setThrottleClickListener
import com.myaxa.domain.HotelId
import com.myaxa.hotel_list_impl.databinding.ItemHotelBinding
import com.myaxa.hotel_list_impl.di.HotelListFragmentScope
import com.myaxa.hotel_list_impl.model.HotelUi

@HotelListFragmentScope
internal class HotelListAdapter(
    private val navigateToDetails: (id: HotelId) -> Unit,
) : ListAdapter<HotelUi, HotelItemViewHolder>(HotelDiffUtilCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotelItemViewHolder {

        val binding = ItemHotelBinding.inflate(parent.inflater, parent, false)

        return HotelItemViewHolder(binding, navigateToDetails)
    }

    override fun onBindViewHolder(holder: HotelItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

internal class HotelItemViewHolder(
    private val binding: ItemHotelBinding,
    private val navigateToDetails: (id: HotelId) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: HotelUi) {

        val context = binding.root.context

        with(binding) {
            title.text = item.name
            address.text = item.address
            rating.text = item.stars.toString()
            distance.text = context.getString(R.string.distance, item.distance.toString())
            freeRoomsNumber.text = context.getString(R.string.free_rooms_number, item.freeRoomsNumber)

            root.setThrottleClickListener {
                navigateToDetails(item.id)
            }
        }
    }
}

internal class HotelDiffUtilCallback() : DiffUtil.ItemCallback<HotelUi>() {
    override fun areItemsTheSame(oldItem: HotelUi, newItem: HotelUi): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: HotelUi, newItem: HotelUi): Boolean {
        return oldItem == newItem
    }
}