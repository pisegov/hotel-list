package com.myaxa.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.myaxa.domain.HotelId
import kotlinx.coroutines.flow.Flow

@Dao
internal interface HotelDao {

    @Upsert
    suspend fun insertHotelList(list: List<HotelDBO>)

    @Upsert
    suspend fun insertHotel(item: HotelDBO)

    @Upsert
    suspend fun insertHotelDetails(item: HotelDetailsDBO)

    @Query("select * from hotel")
    fun getHotelsFlow(): Flow<List<HotelDBO>>

    @Query("select * from hotel where hotel_id=(:id)")
    fun getHotel(id: HotelId): Flow<HotelFullDBO>
}
