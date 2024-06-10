package com.myaxa.hotel_list_impl

import com.myaxa.domain.HotelId
import com.myaxa.domain.HotelRepository
import com.myaxa.domain.model.Hotel
import com.myaxa.hotel_list_impl.model.HotelUi
import com.myaxa.hotel_list_impl.model.toUiModel
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flow

internal val hotelRepositoryMock: HotelRepository = mockk(relaxUnitFun = true) {

    every { hotelsFlow } returns (flow { emit(provideHotelListMock()) })

    every { errorFlow } returns (flow {})
}

internal fun provideHotelMock(
    id: HotelId = 1,
    cacheId: HotelId = 1,
    name: String = "hotel name",
    address: String = "hotel address",
    stars: Float = 4.0f,
    distance: Float = 100f,
    suitesAvailability: String = "1:3",
) = Hotel(id, cacheId, name, address, stars, distance, suitesAvailability)

internal fun provideHotelListMock(): List<Hotel> = listOf(
    provideHotelMock(cacheId = 1, distance = 100f, suitesAvailability = "1:2:3:4"),
    provideHotelMock(cacheId = 2, distance = 200f, suitesAvailability = "2:3"),
    provideHotelMock(cacheId = 3, distance = 50f, suitesAvailability = "1:2:3"),
)

internal fun provideHotelUiListMock(): List<HotelUi> = provideHotelListMock().map { it.toUiModel() }
