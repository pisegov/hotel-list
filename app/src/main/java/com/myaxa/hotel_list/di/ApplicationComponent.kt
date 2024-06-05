package com.myaxa.hotel_list.di

import android.content.Context
import com.myaxa.hotel_details_api.HotelDetailsApi
import com.myaxa.hotel_details_impl.di.HotelDetailsApiModule
import com.myaxa.hotel_list_api.HotelListApi
import com.myaxa.hotel_list_impl.di.HotelListApiModule
import com.myaxa.hotel_list_impl.di.HotelListDependencies
import dagger.BindsInstance
import dagger.Component
import javax.inject.Scope

@ApplicationScope
@Component(
    modules = [
        ApplicationModule::class,
        ViewModelModule::class,
        HotelListApiModule::class,
        HotelDetailsApiModule::class,
    ]
)
internal interface ApplicationComponent: HotelListDependencies {
    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance applicationContext: Context,
        ): ApplicationComponent
    }

    val hotelListApi: HotelListApi
    val hotelDetailsApi: HotelDetailsApi
}

@Scope
internal annotation class ApplicationScope