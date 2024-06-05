package com.myaxa.hotel_list_impl.di

import androidx.fragment.app.Fragment
import com.myaxa.common.SpaceItemDecoration
import com.myaxa.common.dpToPx
import com.myaxa.common.navigator.Navigator
import com.myaxa.hotel_details_api.HotelDetailsApi
import com.myaxa.hotel_list_impl.HotelListAdapter
import dagger.Module
import dagger.Provides

@Module
internal interface HotelListFragmentModule {
    companion object {
        @Provides
        fun provideSpaceItemDecoration(): SpaceItemDecoration {
            return SpaceItemDecoration(12.dpToPx())
        }

        @Provides
        fun provideHotelListAdapter(
            fragment: Fragment,
            hotelDetailsApi: HotelDetailsApi,
            navigator: Navigator,
        ): HotelListAdapter {
            return HotelListAdapter(
                navigateToDetails = { id ->
                    val fragmentManager = fragment.parentFragmentManager
                    val detailsFragment = hotelDetailsApi.provideHotelDetailsFragment(id)

                    navigator.navigateToFragment(fragmentManager, detailsFragment, "hotel details")
                }
            )
        }
    }
}