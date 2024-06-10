package com.myaxa.data

import com.myaxa.domain.HotelId
import com.myaxa.network.model.HotelDTO
import com.myaxa.network.model.HotelDetailsDTO

internal const val HOTEL_ID_MOCK = 1L

internal fun provideHotelDTO(
    id: HotelId = 1,
    name: String = "hotel name",
    address: String = "hotel address",
    stars: Float = 4.0f,
    distance: Float = 100f,
    suitesAvailability: String = "1:0",
) = HotelDTO(id, name, address, stars, distance, suitesAvailability)

internal fun provideHotelDetailsDTO(
    id: HotelId = 1,
    name: String = "hotel name",
    address: String = "hotel address",
    stars: Float = 4.0f,
    distance: Float = 100f,
    suitesAvailability: String = "1:0",
    imageUrl: String = "image url",
    latitude: Double = 1000.0,
    longitude: Double = 2000.0,
) = HotelDetailsDTO(id, name, address, stars, distance, imageUrl, suitesAvailability, latitude, longitude)

internal fun provideHotelDTOList() = listOf(provideHotelDTO(id = 1), provideHotelDTO(id = 2), provideHotelDTO(id = 3))

internal fun provideHotelDBOList() = provideHotelDTOList().map { it.toDBO() }

internal fun provideHotelDetailsDBO() = provideHotelDetailsDTO().toDBO()
