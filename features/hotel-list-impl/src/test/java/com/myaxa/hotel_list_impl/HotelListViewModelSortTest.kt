package com.myaxa.hotel_list_impl

import app.cash.turbine.test
import com.myaxa.common.testing.MainCoroutineDispatcherRule
import com.myaxa.hotel_list_impl.model.HotelUi
import com.myaxa.hotel_list_impl.model.SortingType
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(value = Parameterized::class)
internal class HotelListViewModelSortTest(
    private val sortingType: SortingType,
    private val hotelList: List<HotelUi>,
) {

    @get:Rule
    val mainCoroutineDispatcherRule = MainCoroutineDispatcherRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `should sort hotel list by type`() = runTest {
        val viewModel = createHotelListViewModel()
        val expected = hotelList

        viewModel.initStateFlow()
        advanceUntilIdle()

        viewModel.sortHotelList(sortingType)
        advanceUntilIdle()

        viewModel.screenStateFlow.test {
            assertEquals(expected, awaitItem().hotelList)
        }
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index}: Sorting type: {0}, Sorted list: {1}")
        fun data(): Iterable<Array<Any>> {
            return arrayListOf(
                arrayOf(SortingType.NONE, provideHotelUiListMock().sortedBy { it.cacheId }),
                arrayOf(SortingType.BY_DISTANCE, provideHotelUiListMock().sortedBy { it.distance }),
                arrayOf(SortingType.BY_FREE_ROOMS_NUMBER, provideHotelUiListMock().sortedBy { it.freeRoomsNumber }),
            ).toList()
        }
    }
}
