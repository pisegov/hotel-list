package com.myaxa.hotels_list.di

import android.content.Context
import com.myaxa.common.navigator.Navigator
import com.myaxa.hotel_list_api.HotelListApi
import com.myaxa.hotel_list_impl.di.HotelListApiModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Scope

@ApplicationScope
@Component(
    modules = [
        ApplicationModule::class,
        ViewModelModule::class,
        HotelListApiModule::class,
    ]
)
internal interface ApplicationComponent{
    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance applicationContext: Context,
        ): ApplicationComponent
    }

    val navigator: Navigator
    val hotelListApi: HotelListApi
}

@Scope
internal annotation class ApplicationScope