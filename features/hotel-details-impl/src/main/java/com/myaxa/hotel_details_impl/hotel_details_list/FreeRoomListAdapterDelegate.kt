package com.myaxa.hotel_details_impl.hotel_details_list

import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewbinding.ViewBinding
import com.myaxa.common.SpaceItemDecoration
import com.myaxa.common.inflater
import com.myaxa.hotel_details_impl.databinding.ItemFreeRoomListBinding
import com.myaxa.hotel_details_impl.di.RoomListSpacer
import com.myaxa.hotel_details_impl.model.FreeRoomItem
import com.myaxa.hotel_details_impl.free_rooms_list.FreeRoomListAdapter
import com.myaxa.hotel_details_impl.model.HotelDetailListItem
import javax.inject.Inject

internal class FreeRoomListAdapterDelegate @Inject constructor(
    private val freeRoomListAdapter: FreeRoomListAdapter,
    @param:RoomListSpacer private val spaceItemDecoration: SpaceItemDecoration,
) : AdapterDelegate {

    override val binder: (ViewBinding, HotelDetailListItem) -> Unit = { binding, item ->
        binding as ItemFreeRoomListBinding
        item as HotelDetailListItem.FreeRoomsItem

        freeRoomListAdapter.submitList(item.rooms.map { FreeRoomItem(it.toInt()) })

        with(binding.freeRoomsList) {
            adapter = freeRoomListAdapter

            layoutManager = LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL, false)

            if (itemDecorationCount < 1) addItemDecoration(spaceItemDecoration)
        }
    }

    override fun getViewBinding(root: ViewGroup): ViewBinding {
        return ItemFreeRoomListBinding.inflate(root.inflater, root, false)
    }

    override fun checkViewType(item: HotelDetailListItem): Boolean {
        return item is HotelDetailListItem.FreeRoomsItem
    }
}