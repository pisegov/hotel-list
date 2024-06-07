package com.myaxa.hotel_details_impl.di

import androidx.recyclerview.widget.RecyclerView.RecycledViewPool
import coil.ImageLoader
import com.myaxa.common.SpaceItemDecoration
import com.myaxa.hotel_details_impl.HotelDetailsViewModel
import com.myaxa.hotel_details_impl.hotel_details_list.HotelDetailsAdapter
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
            @BindsInstance viewModel: HotelDetailsViewModel,
            @BindsInstance imageLoader: ImageLoader,
        ): HotelDetailsFragmentComponent
    }

    val viewModel: HotelDetailsViewModel
    val hotelListAdapter: HotelDetailsAdapter
    val spaceItemDecoration: SpaceItemDecoration
    val recycledViewPool: RecycledViewPool
    val imageLoader: ImageLoader
}

@Module(includes = [AdapterDelegatesModule::class, RecyclersExtensionsModule::class])
internal interface HotelDetailsFragmentModule

@Scope
internal annotation class HotelDetailsFragmentScope