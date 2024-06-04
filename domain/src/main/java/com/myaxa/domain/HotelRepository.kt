package com.myaxa.domain

import com.myaxa.domain.model.Hotel
import com.myaxa.domain.model.HotelDetails
import kotlinx.coroutines.flow.Flow

interface HotelRepository {
    val hotelsFlow: Flow<List<Hotel>>

    suspend fun loadHotels()

    suspend fun getHotel(id: HotelId): Flow<HotelDetails>
}