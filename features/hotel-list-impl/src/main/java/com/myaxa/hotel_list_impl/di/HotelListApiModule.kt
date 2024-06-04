package com.myaxa.hotel_list_impl.di

import com.myaxa.hotel_list_api.HotelListApi
import dagger.Binds
import dagger.Module

@Module
abstract class HotelListApiModule {

    @Binds
    internal abstract fun bindHotelListApi(impl: HotelListApiImpl): HotelListApi
}