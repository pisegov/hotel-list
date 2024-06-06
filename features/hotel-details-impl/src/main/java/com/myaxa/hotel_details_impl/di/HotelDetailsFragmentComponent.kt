package com.myaxa.hotel_details_impl.di

import androidx.fragment.app.Fragment
import com.myaxa.hotel_details_impl.HotelDetailsViewModel
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import javax.inject.Scope

@HotelDetailsFragmentScope
@Component(modules = [HotelDetailsFragmentModule::class])
internal interface HotelDetailsFragmentComponent {

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance fragment: Fragment,
            @BindsInstance viewModel: HotelDetailsViewModel,
        ): HotelDetailsFragmentComponent
    }

    val fragment: Fragment
    val viewModel: HotelDetailsViewModel
}

@Module
internal interface HotelDetailsFragmentModule

@Scope
internal annotation class HotelDetailsFragmentScope