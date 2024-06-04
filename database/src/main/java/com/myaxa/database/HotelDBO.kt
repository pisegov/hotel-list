package com.myaxa.database

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.myaxa.domain.HotelId

@Entity(tableName = "hotel")
data class HotelDBO (
    @ColumnInfo("hotel_id") @PrimaryKey val hotelId: HotelId,
    @ColumnInfo("name") val name: String,
    @ColumnInfo("address") val address: String,
    @ColumnInfo("stars") val stars: Float,
    @ColumnInfo("distance") val distance: Float,

)

@Entity(tableName = "hotel_details")
data class HotelDetailsDBO(
    @ColumnInfo("hotel_id") @PrimaryKey val hotelId: HotelId,
    @ColumnInfo("suites_availability") val suitesAvailability: String,
    @ColumnInfo("image") val imageUrl: String,
    @ColumnInfo("lat") val latitude: Double,
    @ColumnInfo("lon") val longitude: Double,
)

data class HotelFullDBO(
    @Embedded val hotel: HotelDBO,
    @Relation(
        parentColumn = "hotel_id",
        entityColumn = "hotel_id",
    )
    val details: HotelDetailsDBO
)