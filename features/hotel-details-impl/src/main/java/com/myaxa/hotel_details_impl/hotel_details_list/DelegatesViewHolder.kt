package com.myaxa.hotel_details_impl.hotel_details_list

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.myaxa.hotel_details_impl.model.HotelDetailListItem

internal class DelegatesViewHolder<in T : HotelDetailListItem>(
    private val binding: ViewBinding,
    private val binder: (binding: ViewBinding, T) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: T) {
        binder(binding, item)
    }
}
