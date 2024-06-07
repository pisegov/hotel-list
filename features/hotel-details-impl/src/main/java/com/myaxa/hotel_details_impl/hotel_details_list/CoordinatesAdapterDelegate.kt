package com.myaxa.hotel_details_impl.hotel_details_list

import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.myaxa.common.inflater
import com.myaxa.hotel_details_impl.R
import com.myaxa.hotel_details_impl.databinding.ItemCoordinatesBinding
import com.myaxa.hotel_details_impl.model.HotelDetailListItem
import javax.inject.Inject

internal class CoordinatesAdapterDelegate @Inject constructor() : AdapterDelegate {
    override val binder: (ViewBinding, HotelDetailListItem) -> Unit = { binding, item ->
        binding as ItemCoordinatesBinding
        item as HotelDetailListItem.CoordinatesCard

        val context = binding.root.context
        binding.latitude.text = context.getString(R.string.latitude, item.latitude)
        binding.longitude.text = context.getString(R.string.longitude, item.longitude)
    }

    override fun getViewBinding(root: ViewGroup): ViewBinding {
        return ItemCoordinatesBinding.inflate(root.inflater, root, false)
    }

    override fun checkViewType(item: HotelDetailListItem): Boolean {
        return item is HotelDetailListItem.CoordinatesCard
    }
}
