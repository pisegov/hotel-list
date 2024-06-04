package com.myaxa.hotel_list_impl.model

import com.myaxa.domain.HotelId
import com.myaxa.domain.model.Hotel

data class HotelUi(
    val id: HotelId,
    val cacheId: Long,
    val name: String,
    val address: String,
    val stars: Float,
    val distance: Float,
    val freeRoomsNumber: Int,
)

fun Hotel.toUiModel() = HotelUi(
    id = id,
    cacheId = cacheId,
    name = name,
    address = address,
    stars = stars,
    distance = distance,
    freeRoomsNumber = suitesAvailability.split(':').count()
)