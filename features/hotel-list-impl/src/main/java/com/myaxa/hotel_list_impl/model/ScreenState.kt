package com.myaxa.hotel_list_impl.model

internal data class ScreenState(
    val hotelList: List<HotelUi> = emptyList(),
    val sortingType: SortingType = SortingType.NONE,
    val isLoading: Boolean = false,
)