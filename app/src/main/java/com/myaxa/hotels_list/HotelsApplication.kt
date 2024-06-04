package com.myaxa.hotels_list

import android.app.Application
import com.myaxa.common.navigator.Navigator
import com.myaxa.common.navigator.NavigatorProvider
import com.myaxa.hotel_list_api.HotelListApi
import com.myaxa.hotel_list_api.HotelListApiProvider
import com.myaxa.hotel_list_impl.HotelListDependencies
import com.myaxa.hotel_list_impl.HotelListDependenciesProvider
import com.myaxa.hotels_list.di.ApplicationComponent
import com.myaxa.hotels_list.di.DaggerApplicationComponent

internal class HotelsApplication : Application(),
                                   NavigatorProvider,
                                   HotelListApiProvider,
                                   HotelListDependenciesProvider {

    private val component: ApplicationComponent by lazy {
        DaggerApplicationComponent.factory().create(applicationContext = this)
    }

    override fun provideNavigator(): Navigator = component.navigator

    override fun provideHotelListApi(): HotelListApi = component.hotelListApi

    override fun provideHotelListDependencies(): HotelListDependencies = component
}