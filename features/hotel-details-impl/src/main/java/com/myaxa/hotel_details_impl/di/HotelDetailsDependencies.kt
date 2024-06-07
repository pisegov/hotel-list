package com.myaxa.hotel_details_impl.di

import androidx.lifecycle.ViewModelProvider
import coil.ImageLoader

interface HotelDetailsDependencies {
    val viewModelFactory: ViewModelProvider.Factory
    val imageLoader: ImageLoader
}

interface HotelDetailsDependenciesProvider {
    fun provideHotelDetailsDependencies(): HotelDetailsDependencies
}