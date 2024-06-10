package com.myaxa.hotel_details_impl

import app.cash.turbine.test
import com.myaxa.common.testing.MainCoroutineDispatcherRule
import com.myaxa.domain.HotelRepository
import com.myaxa.hotel_details_impl.model.toUiModel
import io.mockk.coVerify
import io.mockk.every
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

internal fun createHotelDetailsViewModel(repository: HotelRepository = hotelRepositoryMock): HotelDetailsViewModel {
    return HotelDetailsViewModel(HOTEL_DETAILS_ID_MOCK, repository)
}

@OptIn(ExperimentalCoroutinesApi::class)
internal class HotelDetailsViewModelTest {

    @get:Rule
    val mainCoroutineDispatcherRule = MainCoroutineDispatcherRule()

    private lateinit var viewModel: HotelDetailsViewModel

    @Before
    fun setViewModel() {
        viewModel = createHotelDetailsViewModel()
    }

    @Test
    fun `should set hotel model to screen state on initStateFlow`() = runTest {

        val expected = provideHotelDetailsMock().toUiModel()

        viewModel.initStateFlow()
        advanceUntilIdle()

        viewModel.stateFlow.test {
            assertEquals(expected, awaitItem().hotel)
        }
    }

    @Test
    fun `should set error message to screen state on initStateFlow`() = runTest {

        val expected = "error message"

        every { hotelRepositoryMock.errorFlow } returns (flow { emit(Throwable(expected)) })
        every { hotelRepositoryMock.getHotelDetailsFlow(HOTEL_DETAILS_ID_MOCK) } returns (flow { })

        viewModel.initStateFlow()
        advanceUntilIdle()

        viewModel.stateFlow.test {
            assertEquals(expected, awaitItem().errorText)
        }
    }

    @Test
    fun `should call repository_loadHotelDetails on initStateFlow`() = runTest {

        viewModel.initStateFlow()
        advanceUntilIdle()

        coVerify { hotelRepositoryMock.loadHotelDetails(HOTEL_DETAILS_ID_MOCK) }
    }

    @Test
    fun `should call repository_loadHotelDetails on loadHotelDetails`() = runTest {

        viewModel.loadHotelDetails()
        advanceUntilIdle()

        coVerify { hotelRepositoryMock.loadHotelDetails(HOTEL_DETAILS_ID_MOCK) }
    }

    @Test
    fun `should start refreshing on loadHotelDetails`() = runTest {

        viewModel.stateFlow.test {
            awaitItem() // skip initial value

            viewModel.loadHotelDetails()

            assertEquals(true, awaitItem().isRefreshing)
            assertEquals(false, awaitItem().isRefreshing)
        }
    }

    @Test
    fun `should set error message to null on setErrorWasShown`() = runTest {

        every { hotelRepositoryMock.errorFlow } returns (flow { emit(Throwable("error message")) })

        viewModel.initStateFlow()
        advanceUntilIdle()

        viewModel.setErrorWasShown()
        advanceUntilIdle()

        viewModel.stateFlow.test {
            assertEquals(null, awaitItem().errorText)
        }
    }
}