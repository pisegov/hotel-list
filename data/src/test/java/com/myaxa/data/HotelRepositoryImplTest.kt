package com.myaxa.data

import app.cash.turbine.test
import com.myaxa.database.LocalDataSource
import com.myaxa.domain.HotelRepository
import com.myaxa.network.NetworkDataSource
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HotelRepositoryImplTest {

    private val networkDataSourceMock: NetworkDataSource = mockk()
    private val localDataSourceMock: LocalDataSource = mockk(relaxed = true)

    private fun createHotelRepository(
        networkDataSource: NetworkDataSource = networkDataSourceMock,
        localDataSource: LocalDataSource = localDataSourceMock,
    ) = HotelRepositoryImpl(networkDataSource, localDataSource)

    private lateinit var repository: HotelRepository

    @Before
    fun setRepository() {
        repository = createHotelRepository()
    }

    @Test
    fun `should insert hotel list into database on loadHotels`() = runTest {

        coEvery { networkDataSourceMock.getHotels() } returns (Result.success(provideHotelDTOList()))

        // test there is no interaction with error flow
        // it fails if there are unconsumed emits
        repository.errorFlow.test {
            repository.loadHotels()
        }

        coVerify { localDataSourceMock.insertHotelList(provideHotelDBOList()) }
    }

    @Test
    fun `should emit error to errorFlow on loadHotels`() = runTest {

        val expected = Throwable("error message")
        coEvery { networkDataSourceMock.getHotels() } returns (Result.failure(expected))

        repository.errorFlow.test {

            repository.loadHotels()

            assertEquals(expected, awaitItem())
        }
        advanceUntilIdle()
    }

    @Test
    fun `should insert hotel details model into database on loadHotelDetails`() = runTest {

        coEvery { networkDataSourceMock.getHotel(HOTEL_ID_MOCK) } returns (Result.success(provideHotelDetailsDTO()))

        // test there is no interaction with error flow
        // it fails if there are unconsumed emits
        repository.errorFlow.test {
            repository.loadHotelDetails(HOTEL_ID_MOCK)
        }

        coVerify { localDataSourceMock.insertHotel(provideHotelDetailsDBO()) }
    }

    @Test
    fun `should emit error to errorFlow on loadHotelDetails`() = runTest{

        val expected = Throwable("error message")
        coEvery { networkDataSourceMock.getHotel(HOTEL_ID_MOCK) } returns (Result.failure(expected))

        repository.errorFlow.test {

            repository.loadHotelDetails(HOTEL_ID_MOCK)

            assertEquals(expected, awaitItem())
        }
    }
}
