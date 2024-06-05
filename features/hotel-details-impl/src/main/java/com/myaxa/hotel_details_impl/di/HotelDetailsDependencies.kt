package com.myaxa.hotel_details_impl.di

import androidx.lifecycle.ViewModelProvider

interface HotelDetailsDependencies {
    val viewModelFactory: ViewModelProvider.Factory
}

interface HotelDetailsDependenciesProvider {
    fun provideHotelDetailsDependencies(): HotelDetailsDependencies
}