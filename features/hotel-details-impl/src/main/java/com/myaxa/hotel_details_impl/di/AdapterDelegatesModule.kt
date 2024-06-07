package com.myaxa.hotel_details_impl.di

import com.myaxa.hotel_details_impl.hotel_details_list.AdapterDelegate
import com.myaxa.hotel_details_impl.hotel_details_list.AddressAdapterDelegate
import com.myaxa.hotel_details_impl.hotel_details_list.CoordinatesAdapterDelegate
import com.myaxa.hotel_details_impl.hotel_details_list.FreeRoomListAdapterDelegate
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoSet

@Module
internal interface AdapterDelegatesModule {
    @Binds
    @IntoSet
    fun bindAddressAdapterDelegate(impl: AddressAdapterDelegate): AdapterDelegate

    @Binds
    @IntoSet
    fun bindCoordinatesAdapterDelegate(impl: CoordinatesAdapterDelegate): AdapterDelegate

    @Binds
    @IntoSet
    fun bindFreeRoomsAdapterDelegate(impl: FreeRoomListAdapterDelegate): AdapterDelegate
}