package com.myaxa.hotel_details_impl.model

internal sealed interface HotelDetailListItem {

    data class AddressCard(
        val address: String,
        val distanceToTheCenter: String,
    ) : HotelDetailListItem

    data class FreeRoomsItem(val rooms: List<String>) : HotelDetailListItem

    data class CoordinatesCard(val latitude: String, val longitude: String) : HotelDetailListItem
}