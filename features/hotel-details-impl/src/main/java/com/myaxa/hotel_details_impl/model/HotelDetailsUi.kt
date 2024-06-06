package com.myaxa.hotel_details_impl.model

import com.myaxa.domain.HotelId
import com.myaxa.domain.model.HotelDetails

internal data class HotelDetailsUi (
    val id: HotelId,
    val name: String,
    val address: String,
    val stars: Float,
    val distance: Float,
    val suitesAvailability: List<String>,

    val imageUrl: String?,
    val latitude: Double?,
    val longitude: Double?,
)

internal fun HotelDetails.toUiModel() = HotelDetailsUi(
    id = id,
    name = name,
    address = address,
    stars = stars,
    distance = distance,
    suitesAvailability = suitesAvailability,
    imageUrl = imageUrl,
    latitude = latitude,
    longitude = longitude
)
