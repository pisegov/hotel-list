package com.myaxa.domain

import com.myaxa.domain.model.Hotel
import com.myaxa.domain.model.HotelDetails
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow

interface HotelRepository {
    val hotelsFlow: Flow<List<Hotel>>

    val errorFlow: SharedFlow<Throwable>

    suspend fun loadHotels()

    fun getHotelDetailsFlow(id: HotelId): Flow<HotelDetails>

    suspend fun loadHotelDetails(id: HotelId)
}