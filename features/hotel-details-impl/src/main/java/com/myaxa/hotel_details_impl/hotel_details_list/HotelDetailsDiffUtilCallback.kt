package com.myaxa.hotel_details_impl.hotel_details_list

import androidx.recyclerview.widget.DiffUtil
import com.myaxa.hotel_details_impl.model.HotelDetailListItem

internal class HotelDetailsDiffUtilCallback : DiffUtil.ItemCallback<HotelDetailListItem>() {
    override fun areItemsTheSame(oldItem: HotelDetailListItem, newItem: HotelDetailListItem): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: HotelDetailListItem, newItem: HotelDetailListItem): Boolean {
        return oldItem == newItem
    }
}