package com.myaxa.database

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.myaxa.domain.HotelId

@Entity(
    tableName = "hotel",
    indices = [
        Index("hotel_id", unique = true)
    ],
)
data class HotelDBO(
    @ColumnInfo("cache_id") @PrimaryKey(autoGenerate = true) val cacheId: Long = 0,
    @ColumnInfo("hotel_id") val hotelId: HotelId,
    @ColumnInfo("name") val name: String,
    @ColumnInfo("address") val address: String,
    @ColumnInfo("stars") val stars: Float,
    @ColumnInfo("distance") val distance: Float,
    @ColumnInfo("suites_availability") val suitesAvailability: String,
)

@Entity(tableName = "hotel_details")
data class HotelDetailsDBO(
    @ColumnInfo("hotel_id") @PrimaryKey val hotelId: HotelId,
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
    val details: HotelDetailsDBO?,
)