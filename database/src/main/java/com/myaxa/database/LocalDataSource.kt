package com.myaxa.database

import android.content.Context
import androidx.room.Room
import androidx.room.withTransaction
import com.myaxa.domain.HotelId
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

fun LocalDataSource(applicationContext: Context): LocalDataSource {

    val db = Room.databaseBuilder(
        applicationContext,
        HotelsDatabase::class.java,
        "hotels_database"
    ).build()

    return LocalDataSource(db)
}

class LocalDataSource internal constructor(private val db: HotelsDatabase) {
    private val dao: HotelDao get() = db.dao

    suspend fun insertHotelList(list: List<HotelDBO>) = withContext(Dispatchers.IO) {
        dao.insertHotelList(list)
    }

    suspend fun insertHotel(item: HotelFullDBO) = withContext(Dispatchers.IO) {
        dao.insertHotelDetails(item.details)
    }

    fun getHotelsFlow(): Flow<List<HotelDBO>> = dao.getHotelsFlow()

    fun getHotel(id: HotelId): Flow<HotelFullDBO> = dao.getHotel(id)
}