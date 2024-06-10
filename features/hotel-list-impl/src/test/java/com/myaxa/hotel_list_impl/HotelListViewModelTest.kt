package com.myaxa.hotel_list_impl

import app.cash.turbine.test
import com.myaxa.common.testing.MainCoroutineDispatcherRule
import com.myaxa.domain.HotelRepository
import com.myaxa.hotel_list_impl.model.toUiModel
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

internal fun createHotelListViewModel(repository: HotelRepository = hotelRepositoryMock) = HotelListViewModel(repository)

@OptIn(ExperimentalCoroutinesApi::class)
internal class HotelListViewModelTest {

    @get:Rule
    val mainCoroutineDispatcherRule = MainCoroutineDispatcherRule()

    private lateinit var viewModel: HotelListViewModel

    @Before
    fun setViewModel() {
        viewModel = createHotelListViewModel()
    }

    @Test
    fun `should set hotel model list to screen state on initStateFlow`() = runTest {

        val expected = provideHotelListMock().map { it.toUiModel() }

        viewModel.initStateFlow()
        advanceUntilIdle()

        viewModel.screenStateFlow.test {
            assertEquals(expected, awaitItem().hotelList)
        }
    }

    @Test
    fun `should set error message to screen state on initStateFlow`() = runTest {

        val expected = "error message"

        every { hotelRepositoryMock.errorFlow } returns (flow { emit(Throwable(expected)) })

        viewModel.initStateFlow()
        advanceUntilIdle()

        viewModel.screenStateFlow.test {
            assertEquals(expected, awaitItem().errorText)
        }
    }

    @Test
    fun `should call repository_loadHotels on initStateFlow`() = runTest {

        viewModel.initStateFlow()
        advanceUntilIdle()

        coVerify { hotelRepositoryMock.loadHotels() }
    }

    @Test
    fun `should call repository_loadHotels on loadHotelList`() = runTest {

        viewModel.loadHotelList()
        advanceUntilIdle()

        coVerify { hotelRepositoryMock.loadHotels() }
    }

    @Test
    fun `should start loading on loadHotelDetails`() = runTest {

        // init state to set loading to false
        viewModel.initStateFlow()
        advanceUntilIdle()

        viewModel.screenStateFlow.test {
            awaitItem() // skip initial value

            viewModel.loadHotelList()

            assertEquals(true, awaitItem().isLoading)
            assertEquals(false, awaitItem().isLoading)
        }
    }

    @Test
    fun `should set error message to null on setErrorWasShown`() = runTest {
        every { hotelRepositoryMock.errorFlow } returns (flow { emit(Throwable("error message")) })

        viewModel.initStateFlow()
        advanceUntilIdle()

        viewModel.setErrorWasShown()
        advanceUntilIdle()

        viewModel.screenStateFlow.test {
            assertEquals(null, awaitItem().errorText)
        }
    }
}
