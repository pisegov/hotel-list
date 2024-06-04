package com.myaxa.domain.model

import com.myaxa.domain.HotelId

data class Hotel(
    val id: HotelId,
    val cacheId: Long,
    val name: String,
    val address: String,
    val stars: Float,
    val distance: Float,
    val suitesAvailability: String,
)
