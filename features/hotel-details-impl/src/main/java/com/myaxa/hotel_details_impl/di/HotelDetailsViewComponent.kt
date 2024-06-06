package com.myaxa.hotel_details_impl.di

import androidx.lifecycle.LifecycleOwner
import com.myaxa.hotel_details_impl.HotelDetailsViewController
import com.myaxa.hotel_details_impl.databinding.FragmentHotelDetailsBinding
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import javax.inject.Scope

@HotelDetailsViewScope
@Component(
    dependencies = [HotelDetailsFragmentComponent::class],
    modules = [HotelDetailsViewModule::class]
)
internal interface HotelDetailsViewComponent {
    @Component.Factory
    interface Factory {
        fun create(
            fragmentComponent: HotelDetailsFragmentComponent,
            @BindsInstance binding: FragmentHotelDetailsBinding,
            @BindsInstance lifecycleOwner: LifecycleOwner,
        ): HotelDetailsViewComponent
    }

    val viewController: HotelDetailsViewController
}

@Module
internal interface HotelDetailsViewModule

@Scope
internal annotation class HotelDetailsViewScope