package com.myaxa.hotel_list

import android.app.Application
import com.myaxa.common.navigator.Navigator
import com.myaxa.common.navigator.NavigatorProvider
import com.myaxa.hotel_details_api.HotelDetailsApi
import com.myaxa.hotel_details_api.HotelDetailsApiProvider
import com.myaxa.hotel_details_impl.di.HotelDetailsDependencies
import com.myaxa.hotel_details_impl.di.HotelDetailsDependenciesProvider
import com.myaxa.hotel_list.di.ApplicationComponent
import com.myaxa.hotel_list.di.DaggerApplicationComponent
import com.myaxa.hotel_list_api.HotelListApi
import com.myaxa.hotel_list_api.HotelListApiProvider
import com.myaxa.hotel_list_impl.di.HotelListDependencies
import com.myaxa.hotel_list_impl.di.HotelListDependenciesProvider

internal class HotelsApplication : Application(),
                                   NavigatorProvider,
                                   HotelListApiProvider,
                                   HotelListDependenciesProvider,
                                   HotelDetailsApiProvider,
                                   HotelDetailsDependenciesProvider {

    private val component: ApplicationComponent by lazy(mode = LazyThreadSafetyMode.PUBLICATION) {
        DaggerApplicationComponent.factory().create(applicationContext = this)
    }

    override fun provideNavigator(): Navigator = component.navigator

    override fun provideHotelListApi(): HotelListApi = component.hotelListApi

    override fun provideHotelListDependencies(): HotelListDependencies = component

    override fun provideHotelDetailsApi(): HotelDetailsApi = component.hotelDetailsApi

    override fun provideHotelDetailsDependencies(): HotelDetailsDependencies = component
}