package com.myaxa.hotel_details_api

import androidx.fragment.app.Fragment
import com.myaxa.domain.HotelId

interface HotelDetailsApi {
    fun provideHotelDetailsFragment(id: HotelId): Fragment
}

interface HotelDetailsApiProvider {
    fun provideHotelDetailsApi(): HotelDetailsApi
}