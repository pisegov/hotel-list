package com.myaxa.hotel_list

import android.app.Application
import com.myaxa.common.navigator.Navigator
import com.myaxa.common.navigator.NavigatorProvider
import com.myaxa.hotel_list.di.ApplicationComponent
import com.myaxa.hotel_list.di.DaggerApplicationComponent
import com.myaxa.hotel_list_api.HotelListApi
import com.myaxa.hotel_list_api.HotelListApiProvider
import com.myaxa.hotel_list_impl.HotelListDependencies
import com.myaxa.hotel_list_impl.HotelListDependenciesProvider

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