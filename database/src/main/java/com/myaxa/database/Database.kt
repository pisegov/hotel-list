package com.myaxa.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [HotelDBO::class, HotelDetailsDBO::class], version = 1)
internal abstract class HotelsDatabase : RoomDatabase() {
   abstract val dao: HotelDao
}