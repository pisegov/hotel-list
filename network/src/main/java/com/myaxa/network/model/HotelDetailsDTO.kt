package com.myaxa.network.model

import com.myaxa.domain.HotelId
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HotelDetailsDTO (
    @SerialName("id") val id: HotelId,
    @SerialName("name") val name: String,
    @SerialName("address") val address: String,
    @SerialName("stars") val stars: Float,
    @SerialName("distance") val distance: Float,
    @SerialName("image") val imageUrl: String,
    @SerialName("suites_availability") val suitesAvailability: String,
    @SerialName("lat") val latitude: Double,
    @SerialName("lon") val longitude: Double,
)
