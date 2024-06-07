package com.myaxa.data

import com.myaxa.database.LocalDataSource
import com.myaxa.domain.HotelId
import com.myaxa.domain.HotelRepository
import com.myaxa.domain.model.Hotel
import com.myaxa.domain.model.HotelDetails
import com.myaxa.network.NetworkDataSource
import jakarta.inject.Inject
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.map

class HotelRepositoryImpl @Inject constructor(
    private val networkDataSource: NetworkDataSource,
    private val localDataSource: LocalDataSource,
) : HotelRepository {

    override val hotelsFlow: Flow<List<Hotel>> = localDataSource.getHotelsFlow().map { list ->
        list.map { it.toHotel() }
    }

    override val errorFlow: MutableSharedFlow<Throwable> =
        MutableSharedFlow(replay = 0, extraBufferCapacity = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    override suspend fun loadHotels() {
        val remoteResult = networkDataSource.getHotels()

        val remoteList = remoteResult.getOrElse {
            errorFlow.emit(it)
            return
        }

        localDataSource.insertHotelList(remoteList.map { it.toDBO() })
    }

    override fun getHotelDetailsFlow(id: HotelId): Flow<HotelDetails> = localDataSource.getHotel(id).map { it.toHotelDetails() }

    override suspend fun loadHotelDetails(id: HotelId) {
        val remoteResult = networkDataSource.getHotel(id)

        val remoteHotel = remoteResult.getOrElse {
            errorFlow.emit(it)
            return
        }

        localDataSource.insertHotel(remoteHotel.toDBO())
    }
}




