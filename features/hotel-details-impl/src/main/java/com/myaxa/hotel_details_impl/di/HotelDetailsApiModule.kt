package com.myaxa.hotel_details_impl.di

import com.myaxa.hotel_details_api.HotelDetailsApi
import dagger.Binds
import dagger.Module

@Module
abstract class HotelDetailsApiModule {

    @Binds
    internal abstract fun bindHotelDetailsApi(impl: HotelDetailsApiImpl): HotelDetailsApi
}