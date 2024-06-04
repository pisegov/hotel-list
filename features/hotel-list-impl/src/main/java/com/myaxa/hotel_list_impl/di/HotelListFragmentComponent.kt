package com.myaxa.hotel_list_impl.di

import androidx.fragment.app.Fragment
import com.myaxa.common.navigator.Navigator
import com.myaxa.hotel_list_impl.HotelListViewModel
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import javax.inject.Scope

@HotelListFragmentScope
@Component(modules = [HotelListFragmentModule::class])
internal interface HotelListFragmentComponent {
    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance fragment: Fragment,
            @BindsInstance viewModel: HotelListViewModel,
            @BindsInstance navigator: Navigator,
        ): HotelListFragmentComponent
    }

    val fragment: Fragment
    val viewModel: HotelListViewModel
    val navigator: Navigator
}

@Module
internal interface HotelListFragmentModule

@Scope
internal annotation class HotelListFragmentScope