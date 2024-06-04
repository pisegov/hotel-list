package com.myaxa.hotel_list_api

import androidx.fragment.app.Fragment

interface HotelListApi {
    fun provideHotelListFragment(): Fragment
}

interface HotelListApiProvider {
    fun provideHotelListApi(): HotelListApi
}