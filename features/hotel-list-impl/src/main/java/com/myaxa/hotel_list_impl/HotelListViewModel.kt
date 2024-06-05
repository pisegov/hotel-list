package com.myaxa.hotel_list_impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myaxa.domain.HotelRepository
import com.myaxa.hotel_list_impl.model.ScreenState
import com.myaxa.hotel_list_impl.model.SortingType
import com.myaxa.hotel_list_impl.model.toUiModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class HotelListViewModel @Inject constructor(
    private val repository: HotelRepository,
) : ViewModel() {

    init {
        repository.hotelsFlow.onEach { hotelList ->
            _screenStateFlow.emit(ScreenState(hotelList = hotelList.map { it.toUiModel() }))
        }.onStart {
            repository.loadHotels()
        }.launchIn(viewModelScope)
    }

    private val _screenStateFlow = MutableStateFlow(ScreenState(isLoading = true))
    internal val screenStateFlow = _screenStateFlow.asStateFlow()

    internal fun loadHotelList() {
        viewModelScope.launch {
            repository.loadHotels()
        }
    }

    internal fun sortHotelList(sortingType: SortingType) {

        if (sortingType == _screenStateFlow.value.sortingType) return

        viewModelScope.launch {
            _screenStateFlow.update { it.copy(isLoading = true) }

            // to demonstrate that the loading is actually showing
            delay(600)

            val list = _screenStateFlow.value.hotelList

            val sorted = when (sortingType) {
                SortingType.NONE -> list.sortedBy { it.cacheId }
                SortingType.BY_DISTANCE -> list.sortedBy { it.distance }
                SortingType.BY_FREE_ROOMS_NUMBER -> list.sortedBy { it.freeRoomsNumber }
            }

            _screenStateFlow.update { state ->
                state.copy(hotelList = sorted, sortingType = sortingType, isLoading = false)
            }
        }
    }
}