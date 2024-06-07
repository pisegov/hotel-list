package com.myaxa.hotel_details_impl.hotel_details_list

import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.myaxa.common.inflater
import com.myaxa.hotel_details_impl.databinding.ItemAddressAndDistanceBinding
import com.myaxa.hotel_details_impl.model.HotelDetailListItem
import javax.inject.Inject

internal class AddressAdapterDelegate @Inject constructor(): AdapterDelegate {
    override val binder: (ViewBinding, HotelDetailListItem) -> Unit = { binding, item ->
        binding as ItemAddressAndDistanceBinding
        item as HotelDetailListItem.AddressCard

        binding.address.text = item.address
        binding.distance.text = item.distanceToTheCenter
    }

    override fun getViewBinding(root: ViewGroup): ViewBinding {
        return ItemAddressAndDistanceBinding.inflate(root.inflater, root, false)
    }

    override fun checkViewType(item: HotelDetailListItem): Boolean {
        return item is HotelDetailListItem.AddressCard
    }
}