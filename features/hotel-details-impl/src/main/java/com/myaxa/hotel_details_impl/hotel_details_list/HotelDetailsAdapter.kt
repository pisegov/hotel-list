package com.myaxa.hotel_details_impl.hotel_details_list

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.myaxa.hotel_details_impl.model.HotelDetailListItem
import javax.inject.Inject

internal class HotelDetailsAdapter @Inject constructor(
    delegates: Set<@JvmSuppressWildcards AdapterDelegate>,
) : ListAdapter<HotelDetailListItem,
    DelegatesViewHolder<HotelDetailListItem>>(HotelDetailsDiffUtilCallback()) {

    private val delegates = delegates.toList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DelegatesViewHolder<HotelDetailListItem> {

        val delegate = delegates[viewType]
        val binding = delegate.getViewBinding(parent)
        return DelegatesViewHolder(binding, delegate.binder)
    }

    override fun onBindViewHolder(holder: DelegatesViewHolder<HotelDetailListItem>, position: Int) {
        holder.bind(currentList[position])
    }

    override fun getItemViewType(position: Int): Int {
        delegates.forEachIndexed { index, delegate ->
            if (delegate.checkViewType(currentList[position])) return index
        }
        throw IllegalArgumentException("There is no adapter delegate for this view type")
    }
}
