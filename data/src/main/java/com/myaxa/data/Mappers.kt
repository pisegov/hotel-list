package com.myaxa.data

import com.myaxa.database.HotelDBO
import com.myaxa.database.HotelDetailsDBO
import com.myaxa.database.HotelFullDBO
import com.myaxa.domain.model.Hotel
import com.myaxa.domain.model.HotelDetails
import com.myaxa.network.model.HotelDTO
import com.myaxa.network.model.HotelDetailsDTO

internal fun HotelDTO.toDBO() = HotelDBO(
    hotelId = id,
    name = name,
    address = address,
    stars = stars,
    distance = distance,
)

internal fun HotelDetailsDTO.toDBO() = HotelFullDBO(
    hotel = HotelDBO(
        hotelId = id,
        name = name,
        address = address,
        stars = stars,
        distance = distance,
    ),
    details = HotelDetailsDBO(
        hotelId = id,
        suitesAvailability = suitesAvailability,
        imageUrl = imageUrl,
        latitude = latitude,
        longitude = longitude,
    ),
)

internal fun HotelDBO.toDBO() = Hotel(
    id = hotelId,
    name = name,
    address = address,
    stars = stars,
    distance = distance,
)

internal fun HotelFullDBO.toHotelDetails() = HotelDetails(
    id = hotel.hotelId,
    name = hotel.name,
    address = hotel.address,
    stars = hotel.stars,
    distance = hotel.distance,
    suitesAvailability = details.suitesAvailability.split(":"),
    imageUrl = details.imageUrl,
    latitude = details.latitude,
    longitude = details.longitude
)