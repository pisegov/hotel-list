package com.myaxa.hotel_list_impl

import androidx.lifecycle.ViewModelProvider
import com.myaxa.common.navigator.Navigator

interface HotelListDependenciesProvider {
    fun provideHotelListDependencies(): HotelListDependencies
}

interface HotelListDependencies {
    val viewModelFactory: ViewModelProvider.Factory
    val navigator: Navigator
}
