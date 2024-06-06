package com.myaxa.hotel_details_impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.myaxa.common.ViewModelCreationExtraKey
import com.myaxa.domain.HotelId
import com.myaxa.domain.HotelRepository
import com.myaxa.hotel_details_impl.model.ScreenState
import com.myaxa.hotel_details_impl.model.toUiModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

class HotelDetailsViewModel(
    private val hotelId: HotelId,
    private val repository: HotelRepository,
) : ViewModel() {

    companion object {
        internal val CREATION_EXTRA_HOTEL_ID_KEY = ViewModelCreationExtraKey<HotelId>()
    }

    internal val hotelFlow = repository.getHotelDetailsFlow(hotelId).onStart {
        repository.loadHotelDetails(hotelId)
    }.map {
        ScreenState(hotel = it.toUiModel())
    }.stateIn(viewModelScope, SharingStarted.Lazily, ScreenState(isLoading = true))

    @Suppress("UNCHECKED_CAST")
    class Factory @Inject constructor(
        private val repository: HotelRepository,
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {

            val hotelId = extras[CREATION_EXTRA_HOTEL_ID_KEY]
                ?: throw NoSuchElementException()

            return HotelDetailsViewModel(hotelId, repository) as T
        }
    }
}
