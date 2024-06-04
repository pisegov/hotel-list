package com.myaxa.hotel_list_impl.di

import androidx.lifecycle.LifecycleOwner
import com.myaxa.hotel_list_impl.HotelListViewController
import com.myaxa.hotel_list_impl.databinding.FragmentHotelListBinding
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import javax.inject.Scope

@HotelListViewScope
@Component(dependencies = [HotelListFragmentComponent::class], modules = [HotelListViewModule::class])
internal interface HotelListViewComponent {
    @Component.Factory
    interface Factory {
        fun create(
            fragmentComponent: HotelListFragmentComponent,
            @BindsInstance binding: FragmentHotelListBinding,
            @BindsInstance lifecycleOwner: LifecycleOwner,
        ): HotelListViewComponent
    }

    val viewController: HotelListViewController
}

@Module
internal interface HotelListViewModule

@Scope
internal annotation class HotelListViewScope