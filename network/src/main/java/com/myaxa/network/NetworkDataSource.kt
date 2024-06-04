package com.myaxa.network

import com.myaxa.domain.HotelId
import com.myaxa.network.model.HotelDTO
import com.myaxa.network.model.HotelDetailsDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.create

fun NetworkDataSource(retrofitModule: RetrofitModule) : NetworkDataSource  {

    val hotelsApi: HotelsApi = retrofitModule.retrofit.create()

    return NetworkDataSource(hotelsApi)
}

class NetworkDataSource internal constructor(
    private val api: HotelsApi,
) {
    suspend fun getHotels(): Result<List<HotelDTO>> = withContext(Dispatchers.IO) {
        api.getHotels()
    }

    suspend fun getHotel(id: HotelId): Result<HotelDetailsDTO> = withContext(Dispatchers.IO) {
        api.getHotel(id)
    }
}






