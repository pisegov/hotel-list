package com.myaxa.network

import com.myaxa.domain.HotelId
import com.myaxa.network.model.HotelDTO
import com.myaxa.network.model.HotelDetailsDTO
import retrofit2.http.GET
import retrofit2.http.Path

internal interface HotelsApi {

    @GET("0777.json")
    suspend fun getHotels(): Result<List<HotelDTO>>

    @GET("{id}.json")
    suspend fun getHotel(@Path("id") id: HotelId): Result<HotelDetailsDTO>
}