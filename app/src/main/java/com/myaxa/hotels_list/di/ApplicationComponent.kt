package com.myaxa.hotels_list.di

import android.content.Context
import com.myaxa.domain.HotelRepository
import dagger.BindsInstance
import dagger.Component
import javax.inject.Scope

@ApplicationScope
@Component(
    modules = [
        ApplicationModule::class,
        ViewModelModule::class,
    ]
)
internal interface ApplicationComponent{
    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance applicationContext: Context,
        ): ApplicationComponent
    }
}

@Scope
internal annotation class ApplicationScope