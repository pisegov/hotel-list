package com.myaxa.hotel_details_impl.model

internal data class ScreenState(
    val hotel: HotelDetailsUi? = null,
    val isLoading: Boolean = false,
)