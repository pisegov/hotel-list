package com.myaxa.hotel_list_impl.di

import androidx.fragment.app.Fragment
import com.myaxa.hotel_list_api.HotelListApi
import com.myaxa.hotel_list_impl.HotelListFragment
import javax.inject.Inject

internal class HotelListApiImpl @Inject constructor() : HotelListApi {
    override fun provideHotelListFragment(): Fragment {
        return HotelListFragment()
    }
}