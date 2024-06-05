package com.myaxa.hotel_details_impl.di

import androidx.fragment.app.Fragment
import com.myaxa.domain.HotelId
import com.myaxa.hotel_details_api.HotelDetailsApi
import com.myaxa.hotel_details_impl.HotelDetailsFragment
import javax.inject.Inject

internal class HotelDetailsApiImpl @Inject constructor() : HotelDetailsApi {
    override fun provideHotelDetailsFragment(id: HotelId): Fragment {
        return HotelDetailsFragment.newInstance(id)
    }
}