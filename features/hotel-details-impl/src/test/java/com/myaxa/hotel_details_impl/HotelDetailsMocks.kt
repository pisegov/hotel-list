package com.myaxa.hotel_details_impl

import com.myaxa.domain.HotelId
import com.myaxa.domain.HotelRepository
import com.myaxa.domain.model.HotelDetails
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flow

internal const val HOTEL_DETAILS_ID_MOCK = 1L

internal val hotelRepositoryMock: HotelRepository = mockk(relaxUnitFun = true) {
    every { getHotelDetailsFlow(HOTEL_DETAILS_ID_MOCK) } returns (flow {
        emit(provideHotelDetailsMock())
    })

    every { errorFlow } returns (flow {})
}

internal fun provideHotelDetailsMock(
    id: HotelId = HOTEL_DETAILS_ID_MOCK,
    name: String = "hotel name",
    address: String = "hotel address",
    stars: Float = 4.0f,
    distance: Float = 100f,
    suitesAvailability: List<String> = emptyList(),
    imageUrl: String? = null,
    latitude: Double? = null,
    longitude: Double? = null,
) = HotelDetails(id, name, address, stars, distance, suitesAvailability, imageUrl, latitude, longitude)