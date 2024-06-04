package com.myaxa.domain.model

import com.myaxa.domain.HotelId

data class HotelDetails(
    val id: HotelId,
    val name: String,
    val address: String,
    val stars: Float,
    val distance: Float,
    val suitesAvailability: List<String>,

    val imageUrl: String,
    val latitude: Double,
    val longitude: Double,
)
