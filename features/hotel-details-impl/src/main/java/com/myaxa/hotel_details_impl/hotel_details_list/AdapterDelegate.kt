package com.myaxa.hotel_details_impl.hotel_details_list

import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.myaxa.hotel_details_impl.model.HotelDetailListItem

internal interface AdapterDelegate {

    val binder: (ViewBinding, HotelDetailListItem) -> Unit

    fun getViewBinding(root: ViewGroup): ViewBinding

    fun checkViewType(item: HotelDetailListItem): Boolean
}